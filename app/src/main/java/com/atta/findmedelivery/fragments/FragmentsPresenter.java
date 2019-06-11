package com.atta.findmedelivery.fragments;

import com.atta.findmedelivery.model.APIService;
import com.atta.findmedelivery.model.APIUrl;
import com.atta.findmedelivery.model.DeliveryOrdersResult;
import com.atta.findmedelivery.model.Order;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentsPresenter implements FragmentsContract.Presenter {


    private FragmentsContract.View mView;


    public FragmentsPresenter(FragmentsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void getOrders(int userId, String category) {

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //User user = new_icon User(name, email, password, phone, birthdayString, locationSting);

        Call<DeliveryOrdersResult> call;

        switch (category){
            case "current":

                //defining the call
                call = service.getCurrentOrders(userId);
                break;
            case "old":

                //defining the call
                call = service.getOldOrders(userId);
                break;
            default:

                //defining the call
                call = service.getCurrentOrders(userId);
                break;
        }


        //calling the api
        call.enqueue(new Callback<DeliveryOrdersResult>() {
            @Override
            public void onResponse(Call<DeliveryOrdersResult> call, Response<DeliveryOrdersResult> response) {

                if (response.body() != null){
                    if (!response.body().getError()){

                        if (response.body().getOrders() != null){


                            ArrayList<Order> orders = response.body().getOrders();

                            if (orders.size() > 0){

                                mView.showRecyclerView(orders);
                            }else {

                                mView.showMessage("No orders added");
                            }
                        }else {

                            mView.showMessage("No orders added");
                        }

                    }
                }else {
                    mView.showMessage("An error");
                }

            }

            @Override
            public void onFailure(Call<DeliveryOrdersResult> call, Throwable t) {

                mView.showMessage(t.getMessage());
            }
        });
    }

}
