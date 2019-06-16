package com.atta.findmedelivery.products;

import android.content.Context;

import com.atta.findmedelivery.model.APIService;
import com.atta.findmedelivery.model.APIUrl;
import com.atta.findmedelivery.model.Product;
import com.atta.findmedelivery.model.Products;
import com.atta.findmedelivery.model.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsPresenter implements ProductsContract.Presenter {


    private ProductsContract.View mView;

    private Context mContext;


    public ProductsPresenter(Context mContext, ProductsContract.View mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getProducts(String cat) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new User(name, email, password, phone, birthdayString, locationSting);

        //defining the call
        Call<Products> call = service.getCategoryProducts(cat);

        //calling the api
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                if (response.body() != null){

                        if (response.body().getProducts() != null){


                            ArrayList<Product> products = response.body().getProducts();

                            if (products.size() > 0){

                                mView.showRecyclerView(products);

                            }
                        }else {
                            mView.showMessage("An error");
                        }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }


    @Override
    public void addShopProduct(int shopId, int itemId, int stock, double price) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new User(name, email, password, phone, birthdayString, locationSting);

        //defining the call
        Call<Result> call = service.addShopProduct(shopId, itemId, stock, price);

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null){

                    if (response.body().getMessage() != null){



                        mView.showMessage(response.body().getMessage());
                    }else {
                        mView.showMessage("An error");
                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }

}
