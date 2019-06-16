package com.atta.findmedelivery.menu;

import com.atta.findmedelivery.model.Product;
import com.atta.findmedelivery.model.Shop;

import java.util.ArrayList;

public interface ShopMenuContract {

    interface View {

        void showRecyclerView(ArrayList<Product> products);

        void showMessage(String message);

        void setCategory(Shop shop);

        void showPopup(int id, double price, int stock);
    }

    interface Presenter {

        void getProducts(String cat, int shopId);

        void getShopCategory(int userId);

        void updateShopProduct(int shopId, int itemId, int stock, double price);
    }
}
