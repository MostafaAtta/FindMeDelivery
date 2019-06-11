package com.atta.findmedelivery.orderdetails;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.main.MainActivity;
import com.atta.findmedelivery.model.Order;
import com.atta.findmedelivery.model.Product;

import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity implements View.OnClickListener, OrderDetailsContract.View{

    Order order;

    TextView timeTv, addressTv, mobileTv, subTotalTv, deliveryFeesTv, discountTv, totalTx;

    RecyclerView recyclerView;

    ImageView submittedImage, preparedImage, deliveredImage, backtoMain;

    OrderDetailsPresenter orderDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        initiateViews();

        orderDetailsPresenter = new OrderDetailsPresenter(this);

        if (getIntent().getSerializableExtra("order") != null){
            order = (Order) getIntent().getSerializableExtra("order");

            setOrderData();
        }


    }


    private void initiateViews() {
        // National ID, Password input text
        timeTv = findViewById(R.id.tv_delivery_time_sum);
        addressTv = findViewById(R.id.tv_address_sum);
        addressTv.setOnClickListener(this);
        mobileTv = findViewById(R.id.tv_phone_sum);
        mobileTv.setOnClickListener(this);
        subTotalTv = findViewById(R.id.tv_subtotal_sum);
        deliveryFeesTv = findViewById(R.id.tv_delivery_sum);
        discountTv = findViewById(R.id.tv_discount_sum);
        totalTx = findViewById(R.id.tv_total_sum);

        recyclerView = findViewById(R.id.recycler_cart2);


        submittedImage = findViewById(R.id.imageView);
        preparedImage = findViewById(R.id.imageView2);
        deliveredImage = findViewById(R.id.imageView3);

        backtoMain  = findViewById(R.id.btn_back_to_main);
        backtoMain.setOnClickListener(this);

    }

    private void setOrderData() {
        timeTv.setText(order.getOrderTime());
        addressTv.setText(order.getFullAddress().getFullAddress());
        mobileTv.setText(order.getMobile());

        switch (order.getStatus()){
            case 0:
                submittedImage.setImageResource(R.drawable.dot_and_circle);
                preparedImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.circle);
                preparedImage.setOnClickListener(this);
                break;

            case 1:
                submittedImage.setImageResource(R.drawable.circle);
                preparedImage.setImageResource(R.drawable.dot_and_circle);
                deliveredImage.setImageResource(R.drawable.circle);
                preparedImage.setOnClickListener(null);
                deliveredImage.setOnClickListener(this);
                break;

            case 2:
                submittedImage.setImageResource(R.drawable.circle);
                preparedImage.setImageResource(R.drawable.circle);
                deliveredImage.setImageResource(R.drawable.dot_and_circle);
                preparedImage.setOnClickListener(null);
                deliveredImage.setOnClickListener(null);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v == addressTv){


            double latitude = order.getFullAddress().getLatitude();
            double longitude = order.getFullAddress().getLongitude();
            String label = "Deliver Address!";
            String uriBegin = "geo:" + latitude + "," + longitude;
            String query = latitude + "," + longitude + "(" + label + ")";
            String encodedQuery = Uri.encode(query);
            String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
            Uri uri = Uri.parse(uriString);
            Intent mapIntent = new Intent(android.content.Intent.ACTION_VIEW, uri);
            startActivity(mapIntent);


        }else if (v == mobileTv){

            String mobile = order.getMobile();
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + mobile));
            startActivity(intent);
        }else if (v == preparedImage){

            submittedImage.setImageResource(R.drawable.circle);
            preparedImage.setImageResource(R.drawable.dot_and_circle);
            deliveredImage.setImageResource(R.drawable.circle);
            preparedImage.setOnClickListener(null);
            deliveredImage.setOnClickListener(this);

            orderDetailsPresenter.updateOrderStatus(1, order.getId());
        }else if (v == deliveredImage){

            submittedImage.setImageResource(R.drawable.circle);
            preparedImage.setImageResource(R.drawable.circle);
            deliveredImage.setImageResource(R.drawable.dot_and_circle);
            preparedImage.setOnClickListener(null);
            deliveredImage.setOnClickListener(null);
            orderDetailsPresenter.updateOrderStatus(2, order.getId());
        }else if (v == backtoMain){

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void showMessage(String error) {

        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Product> products) {

    }


}
