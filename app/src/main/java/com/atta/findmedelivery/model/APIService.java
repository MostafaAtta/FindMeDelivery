package com.atta.findmedelivery.model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {



    @FormUrlEncoded
    @POST("get_shops_by_category")
    Call<Shops> getShops(
            @Field("category") String cat
    );


    @FormUrlEncoded
    @POST("get_products_by_user")
    Call<Products> getProducts(
            @Field("category") String cat,
            @Field("user_id") int shopId
    );


    @FormUrlEncoded
    @POST("get_products_by_category")
    Call<Products> getCategoryProducts(
            @Field("category") String cat
    );


    @FormUrlEncoded
    @POST("add_shop_product")
    Call<Result> addShopProduct(
            @Field("shop_id") int shopId,
            @Field("item_id") int itemId,
            @Field("stock") int stock,
            @Field("price") double price
    );

    @FormUrlEncoded
    @POST("update_shop_product")
    Call<Result> updateShopProduct(
            @Field("shop_id") int shopId,
            @Field("item_id") int itemId,
            @Field("stock") int stock,
            @Field("price") double price
    );

    //the signin call
    @FormUrlEncoded
    @POST("delivery_login")
    Call<Result> userLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    //the signin call
    @FormUrlEncoded
    @POST("admin_login")
    Call<Result> adminLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("creat_shop_user")
    Call<Result> createShopUser(
            @Field("user_name") String username,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("add_shop")
    Call<Result> addShop(
            @Field("admin_id") int userId,
            @Field("shop_name") String name,
            @Field("shop_disc") String desc,
            @Field("phone") String phone,
            @Field("category") String category,
            @Field("district") String location,
            @Field("city") String city,
            @Field("logo_image") String logoImage,
            @Field("bg_image") String bgImage
    );


    @FormUrlEncoded
    @POST("get_shop")
    Call<ShopResult> getShopCategory(
            @Field("user_id") int id
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


    @GET("get_all_current_orders")
    Call<DeliveryOrdersResult> getAllCurrentOrders(
    );


    @GET("get_all_old_orders")
    Call<DeliveryOrdersResult> getAllOldOrders(
    );

    @FormUrlEncoded
    @POST("update_order_status")
    Call<Result> updateOrderStatus(
            @Field("status") int status,
            @Field("order_id") int orderId
    );
}
