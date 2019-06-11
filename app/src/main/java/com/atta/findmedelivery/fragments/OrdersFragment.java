package com.atta.findmedelivery.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.model.Order;
import com.atta.findmedelivery.model.OrdersAdapter;
import com.atta.findmedelivery.model.SessionManager;
import com.atta.findmedelivery.orderdetails.OrderDetailsActivity;

import java.util.ArrayList;

public class OrdersFragment extends Fragment implements FragmentsContract.View {

    FragmentsPresenter fragmentsPresenter;

    RecyclerView recyclerView;

    String category;

    OrdersAdapter myAdapter;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        //searchPresenter = new_icon SearchPresenter(this, getContext());
        //return rootView;
        android.view.View view = inflater.inflate(R.layout.fragment,container,false);
        recyclerView = view.findViewById(R.id.recycler);

        fragmentsPresenter = new FragmentsPresenter(this);


        if (getArguments() != null) {

            category = getArguments().getString("category");

            fragmentsPresenter.getOrders(SessionManager.getInstance(getContext()).getUserId(),category);
        }




        final SwipeRefreshLayout mySwipeRefreshLayout = view.findViewById(R.id.swiperefresh);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        //searchPresenter.getProperties();
                        mySwipeRefreshLayout.setRefreshing(false);


                        fragmentsPresenter.getOrders(SessionManager.getInstance(getContext()).getUserId(),category);
                    }
                }
        );

/*
        mReceiver = new_icon BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if ("action_category_updated".equals(intent.getAction())) {

                    String category = intent.getStringExtra("category");
                    fragmentsPresenter.getShops(category);
                }
            }
        };
        filter = new_icon IntentFilter("action_category_updated");

        getActivity().registerReceiver(mReceiver, filter);*/

        return view;
    }
    @Override
    public void showMessage(String error) {

        Toast.makeText(getContext(),error,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRecyclerView(ArrayList<Order> orders) {
        myAdapter = new OrdersAdapter(this, orders);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }


    @Override
    public void showOrderDetails(Order order) {

        Intent intent = new Intent(getContext(), OrderDetailsActivity.class);
        intent.putExtra("order", order);
        startActivity(intent);
    }
}
