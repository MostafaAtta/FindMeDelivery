package com.atta.findmedelivery.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {



    @FormUrlEncoded
    @POST("get_shops_by_category")
    Call<Shops> getShops(
            @Field("category") String cat
    );


    @FormUrlEncoded
    @POST("get_products")
    Call<Products> getProducts(
            @Field("category") String cat,
            @Field("shop_id") int shopId
    );



    //the signin call
    @FormUrlEncoded
    @POST("delivery_login")
    Call<Result> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("get_order_products")
    Call<Products> getOrderProducts(
            @Field("order_id") int id
    );



    @FormUrlEncoded
    @POST("get_delivery_current_orders")
    Call<DeliveryOrdersResult> getCurrentOrders(
            @Field("delivery_id") int userId
    );


    @FormUrlEncoded
    @POST("get_delivery_old_orders")
    Call<DeliveryOrdersResult> getOldOrders(
            @Field("delivery_id") int userId
    );


    @FormUrlEncoded
    @POST("update_order_status")
    Call<Result> updateOrderStatus(
            @Field("status") int status,
            @Field("order_id") int orderId
    );
}
