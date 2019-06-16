package com.atta.findmedelivery.menu;

import android.content.Context;

import com.atta.findmedelivery.model.APIService;
import com.atta.findmedelivery.model.APIUrl;
import com.atta.findmedelivery.model.Product;
import com.atta.findmedelivery.model.Products;
import com.atta.findmedelivery.model.Result;
import com.atta.findmedelivery.model.ShopResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopMenuPresenter implements ShopMenuContract.Presenter {


    private ShopMenuContract.View mView;

    private Context mContext;


    public ShopMenuPresenter(Context mContext, ShopMenuContract.View mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void getProducts(String cat, int userId) {

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
        Call<Products> call = service.getProducts(cat, userId);

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
    public void getShopCategory(int userId) {

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
        Call<ShopResult> call = service.getShopCategory(userId);

        //calling the api
        call.enqueue(new Callback<ShopResult>() {
            @Override
            public void onResponse(Call<ShopResult> call, Response<ShopResult> response) {

                if (response.body() != null){

                    if (response.body().getShop() != null){



                        mView.setCategory(response.body().getShop());
                    }else {
                        mView.showMessage("An error");
                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<ShopResult> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }



    @Override
    public void updateShopProduct(int shopId, int itemId, int stock, double price) {

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
        Call<Result> call = service.updateShopProduct(shopId, itemId, stock, price);

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
