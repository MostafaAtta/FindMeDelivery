package com.atta.findmedelivery.menu;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.model.Product;
import com.atta.findmedelivery.model.ProductsAdapter;
import com.atta.findmedelivery.model.SessionManager;
import com.atta.findmedelivery.model.Shop;
import com.atta.findmedelivery.products.ProductsActivity;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements ShopMenuContract.View, View.OnClickListener {


    RecyclerView recyclerView;

    ShopMenuPresenter shopMenuPresenter;

    ImageView addImageView, exitImageView;

    Shop shop;

    int shopId;

    String category;

    Dialog dialog;

    String price, stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        recyclerView = findViewById(R.id.recycler);

        addImageView = findViewById(R.id.add);
        exitImageView = findViewById(R.id.exit);
        addImageView.setOnClickListener(this);
        exitImageView.setOnClickListener(this);

        shopMenuPresenter = new ShopMenuPresenter( this, this);

        shopMenuPresenter.getShopCategory(SessionManager.getInstance(this).getUserId());

    }


    @Override
    public void showRecyclerView(ArrayList<Product> products) {

        ProductsAdapter myAdapter = new ProductsAdapter(this, this,  products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setCategory(Shop shop) {

        this.category = shop.getCategory();
        shopId = shop.getId();

        shopMenuPresenter.getProducts(category, SessionManager.getInstance(this).getUserId());
    }

    @Override
    public void showPopup(final int id, final double price, final int stock) {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.product_popup);

        final EditText priceText, stockText;

        Button button;

        priceText = dialog.findViewById(R.id.price);

        stockText = dialog.findViewById(R.id.stock);

        priceText.setText(String.valueOf(price));
        stockText.setText(String.valueOf(stock));

        button = dialog.findViewById(R.id.update);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopMenuPresenter.updateShopProduct(shopId, id, Integer.valueOf(stockText.getText().toString()), Double.valueOf(priceText.getText().toString()));
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        if (v == exitImageView){

            SessionManager.getInstance(this).logoutUser();
            finish();
        }else if (v == addImageView){
            Intent intent = new Intent(MenuActivity.this, ProductsActivity.class);
            intent.putExtra("category", category);
            intent.putExtra("shop_id", shopId);
            startActivity(intent);
        }
    }


}
