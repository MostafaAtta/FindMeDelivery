package com.atta.findmedelivery.fragments;

import com.atta.findmedelivery.model.Order;

import java.util.ArrayList;

public interface FragmentsContract {

    interface View {


        void showMessage(String error);

        void showRecyclerView(ArrayList<Order> orders);

        void showOrderDetails(Order order);
    }

    interface Presenter {

        void getOrders(int userId, String category) ;
    }
}
