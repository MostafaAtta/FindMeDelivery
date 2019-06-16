package com.atta.findmedelivery.products;

import com.atta.findmedelivery.model.Product;

import java.util.ArrayList;

public interface ProductsContract {

    interface View {

        void showRecyclerView(ArrayList<Product> products);

        void showMessage(String message);

        void showPopup(int id);
    }

    interface Presenter {

        void getProducts(String cat);

        void addShopProduct(int shopId, int itemId, int stock, double price);
    }
}
