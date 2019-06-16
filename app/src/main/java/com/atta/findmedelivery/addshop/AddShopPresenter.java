package com.atta.findmedelivery.addshop;

import android.content.Context;

import com.atta.findmedelivery.model.APIService;
import com.atta.findmedelivery.model.APIUrl;
import com.atta.findmedelivery.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddShopPresenter implements AddShopContract.Presenter {


    private AddShopContract.View mView;

    private Context mContext;


    public AddShopPresenter(Context mContext, AddShopContract.View mView) {
        this.mView = mView;
        this.mContext = mContext;
    }

    @Override
    public void createShopUser(String username, String password, final String name, final String desc, final String phone, final String category, final String location,
                               final String city, final String logoImage, final String bgImage) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new User(name, email, password, shopPhone, birthdayString, locationSting);

        //defining the call
        Call<Result> call = service.createShopUser(username, password);

        //calling the api
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (response.body() != null){

                        if (!response.body().getError()){

                            int id = response.body().getUser().getId();

                            addShop(id, name, desc, phone, category, location, city, logoImage, bgImage);

                        }else {
                            mView.showMessage(response.body().getMessage());
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


    @Override
    public void addShop(int userId, String name, String desc, String phone, String category, String location, String city, String logoImage, String bgImage) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new User(name, email, password, shopPhone, birthdayString, locationSting);

        //defining the call
        Call<Result> call = service.addShop(userId, name, desc, phone, category, location, city, logoImage, bgImage);

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
