package com.atta.findmedelivery.orderdetails;

import com.atta.findmedelivery.model.Product;

import java.util.ArrayList;

public interface OrderDetailsContract {

    interface View{

        void showMessage(String error);

        void showRecyclerView(ArrayList<Product> products);
    }

    interface Presenter{

        void getOrderDishes(int orderId) ;

        void updateOrderStatus(int status, int orderId);

    }
}
