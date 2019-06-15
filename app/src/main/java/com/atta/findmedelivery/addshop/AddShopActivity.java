package com.atta.findmedelivery.addshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddShopActivity extends AppCompatActivity implements AddShopContract.View, AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText username, password, passwordConfirm, shopName, shopDesc, phone;

    List<String> categories, cities, districts;

    Spinner categorySpinner, citySpinner, districtSpinner;

    ArrayAdapter<String> categoryAdapter, cityAdapter, districtAdapter;

    String category, city, district;

    Button logoBtn, bgBtn, addBtn;

    AddShopPresenter addShopPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);

        initiateArrays();
        initiateViews();

        addShopPresenter = new AddShopPresenter(this, this);
    }


    @Override
    public void initiateArrays() {


        String[] categoriesArray = getResources().getStringArray(R.array.categories);

        categories = Arrays.asList(categoriesArray);


        String[] citiesArray = getResources().getStringArray(R.array.cities);

        cities = Arrays.asList(citiesArray);


        String[] districtsArray = getResources().getStringArray(R.array.locations);

        districts = Arrays.asList(districtsArray);
    }


    @Override
    public void initiateViews() {

        username = findViewById(R.id.name);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.password_confirm);
        shopName = findViewById(R.id.shop_name);
        shopDesc = findViewById(R.id.shop_desc);
        phone = findViewById(R.id.shop_phone);

        categorySpinner = findViewById(R.id.category);
        citySpinner = findViewById(R.id.city);
        districtSpinner = findViewById(R.id.district);

        logoBtn = findViewById(R.id.logo_btn);
        bgBtn = findViewById(R.id.bg_btn);
        addBtn = findViewById(R.id.add_shop);


        categoryAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(this);


        districtAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, districts);
        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        districtSpinner.setAdapter(districtAdapter);
        districtSpinner.setOnItemSelectedListener(this);

        cityAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);
        citySpinner.setOnItemSelectedListener(this);

        logoBtn.setOnClickListener(this);
        bgBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void showRecyclerView(ArrayList<Product> products) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showPopup(int id) {

    }

    @Override
    public void onClick(View v) {

        if (v == addBtn){
            if (!validate(username.getText().toString(),password.getText().toString(),passwordConfirm.getText().toString())){
                return;
            }
            addShopPresenter.createShopUser(username.getText().toString(),password.getText().toString());

        }
    }


    @Override
    public boolean validate(String name, String pass, String passConfirm) {
        boolean valid = true;



        if (name.isEmpty()) {

            username.setError("Enter your name");
            valid = false;
        } else {

            username.setError(null);
        }


        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 10) {
            password.setError("password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (passConfirm.isEmpty() || passConfirm.length() < 4 || passConfirm.length() > 10 ) {

            passwordConfirm.setError("password must be between 4 and 10 alphanumeric characters");
            valid = false;
        } else if (!pass.equals(passConfirm)){

            password.setError("passwords not Matched");
            valid = false;
        }else {
            password.setError(null);
            passwordConfirm.setError(null);
        }


        return valid;
    }
}
