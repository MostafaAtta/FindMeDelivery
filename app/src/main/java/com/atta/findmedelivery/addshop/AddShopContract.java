package com.atta.findmedelivery.addshop;

import com.atta.findmedelivery.model.Product;

import java.util.ArrayList;

public interface AddShopContract {

    interface View {

        void showRecyclerView(ArrayList<Product> products);

        void showMessage(String message);

        void showPopup(int id);

        void initiateArrays();

        void initiateViews();

        boolean validate(String name, String password, String passwordConfirm, String shopName, String desc, String phone);
    }

    interface Presenter {

        void createShopUser(String username, String password, String name, String desc, String phone, String category, String location, String city, String logoImage, String bgImage);

        void addShop(int userId, String name, String desc, String phone, String category, String location, String city, String logoImage, String bgImage);
    }
}
