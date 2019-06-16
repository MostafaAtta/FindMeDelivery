package com.atta.findmedelivery.products;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.model.Product;
import com.atta.findmedelivery.model.ProductsAdapter;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity implements ProductsContract.View{


    RecyclerView recyclerView;

    ProductsPresenter productsPresenter;

    String category;

    String price, stock;

    int shopId;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        category = getIntent().getExtras().getString("category");

        shopId = getIntent().getExtras().getInt("shop_id");

        recyclerView = findViewById(R.id.recycler);

        productsPresenter = new ProductsPresenter( this, this);

        productsPresenter.getProducts(category);
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
    public void showPopup(final int id) {
        dialog = new Dialog(this);

        dialog.setContentView(R.layout.product_popup);

        final EditText priceText, stockText;

        TextView title;

        Button button;

        priceText = dialog.findViewById(R.id.price);

        stockText = dialog.findViewById(R.id.stock);

        title = dialog.findViewById(R.id.title_text);

        button = dialog.findViewById(R.id.update);

        title.setText("Add product");
        button.setText("Add");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productsPresenter.addShopProduct(shopId, id, Integer.valueOf(stockText.getText().toString()), Double.valueOf(priceText.getText().toString()));
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

}
