package com.atta.findmedelivery.orderdetails;

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

public class OrderDetailsPresenter implements OrderDetailsContract.Presenter {

    private OrderDetailsContract.View mView;


    public OrderDetailsPresenter(OrderDetailsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getOrderDishes(int orderId) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new_icon User(name, email, password, phone, birthdayString, locationSting);

        //defining the call
        Call<Products> call = service.getOrderProducts(orderId);

        //calling the api
        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {

                if (response.body() != null){

                        if (response.body().getProducts() != null){


                            ArrayList<Product> products = response.body().getProducts();

                            if (products.size() > 0){

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
    public void updateOrderStatus(int status, int orderId) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new_icon User(name, email, password, phone, birthdayString, locationSting);

        //defining the call
        Call<Result> call = service.updateOrderStatus(status, orderId);

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null){


                    mView.showMessage(response.body().getMessage());

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
