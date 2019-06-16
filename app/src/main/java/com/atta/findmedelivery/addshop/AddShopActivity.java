package com.atta.findmedelivery.addshop;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.model.Product;
import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.features.ReturnMode;
import com.esafirm.imagepicker.model.Image;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddShopActivity extends AppCompatActivity implements AddShopContract.View, AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText username, password, passwordConfirm, shopName, shopDesc, shopPhone;

    List<String> categories, cities, districts;

    Spinner categorySpinner, citySpinner, districtSpinner;

    ArrayAdapter<String> categoryAdapter, cityAdapter, districtAdapter;

    String category, city, district, logoImage, bgImage;

    Button logoBtn, bgBtn, addBtn;

    AddShopPresenter addShopPresenter;

    boolean logo;

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
        shopPhone = findViewById(R.id.shop_phone);

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

        if (parent == districtSpinner){
            if (position != 0){

                if (position != 1){
                    districtSpinner.setSelection(1);
                    district = districts.get(1);
                    //locationString = "Maadi";
                    showMessage("Available on 6 of October only, Coming Soon");
                }else {
                    district = districts.get(position);


                }
            }else {
                district = null;
            }
        }else if (parent == citySpinner){
            if (position != 0){

                city = cities.get(position);


            }else {
                city = null;
            }
        }else if (parent == categorySpinner){
            if (position != 0){

                category = categories.get(position);


            }else {
                category = null;
            }
        }
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


    public void getImages(){
        ImagePicker.create(this)
                .returnMode(ReturnMode.NONE)// set whether pick and / or camera action should return immediate result or not.
                .folderMode(true) // folder mode (false by default)
                .toolbarFolderTitle("Folder") // folder selection title
                .toolbarImageTitle("Tap to select") // image selection title
                .toolbarArrowColor(Color.BLACK) // Toolbar 'up' arrow color
                .multi() // multi mode (default mode)
                .limit(1) // max images can be selected (99 by default)
                .showCamera(true) // show camera or not (true by default)
                .enableLog(false) // disabling log
                .start();
    }

    @Override
    public void onClick(View v) {

        if (v == addBtn){
            if (!validate(username.getText().toString(),password.getText().toString(),passwordConfirm.getText().toString(), shopName.getText().toString(), shopDesc.getText().toString(),
                    shopPhone.getText().toString())){
                return;
            }
            addShopPresenter.createShopUser(username.getText().toString(),password.getText().toString(), shopName.getText().toString(), shopDesc.getText().toString(),
                    shopPhone.getText().toString(), category, district, city, logoImage, bgImage);

        }else if (v == logoBtn){

            logo = true;
            getImages();

        }else if (v == bgBtn){

            logo = false;
            getImages();

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> imageList = ImagePicker.getImages(data);

            if (!imageList.isEmpty()|| imageList != null){

                if (logo) {
                    logoImage = getStringImage(getBitmapFromPath(imageList.get(0).getPath()));
                }else {

                    bgImage = getStringImage(getBitmapFromPath(imageList.get(0).getPath()));
                }
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private Bitmap getBitmapFromPath(String filePath) {

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(filePath,bmOptions);
        return bitmap;

    }

    public String getStringImage(Bitmap bm){
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG,100,ba);
        byte[] imageByte = ba.toByteArray();
        String encode = Base64.encodeToString(imageByte,Base64.DEFAULT);
        return encode;
    }


    @Override
    public boolean validate(String name, String pass, String passConfirm, String shopNameTxt, String desc, String phone) {
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


        if (shopNameTxt.isEmpty()) {

            shopName.setError("Enter your name");
            valid = false;
        } else {

            shopName.setError(null);
        }


        if (desc.isEmpty()) {

            shopDesc.setError("Enter your name");
            valid = false;
        } else {

            shopDesc.setError(null);
        }

        if (phone.isEmpty()) {

            shopPhone.setError("Enter your name");
            valid = false;
        } else {

            shopPhone.setError(null);
        }


        if (category == null) {

            showMessage("select category");
            valid = false;
        }



        if (city == null) {

            showMessage("select city");
            valid = false;
        }



        if (district == null) {

            showMessage("select location");
            valid = false;
        }



        if (logoImage == null) {

            showMessage("select logo image");
            valid = false;
        }



        if (bgImage == null) {

            showMessage("select background image");
            valid = false;
        }


        return valid;
    }
}
