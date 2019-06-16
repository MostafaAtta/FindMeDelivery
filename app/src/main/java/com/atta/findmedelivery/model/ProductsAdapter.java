package com.atta.findmedelivery.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atta.findmedelivery.R;
import com.atta.findmedelivery.menu.ShopMenuContract;
import com.atta.findmedelivery.products.ProductsContract;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.MyViewHolder> {


    private List<Product> products;

    Context mContext;
    ProductsContract.View productsView;
    ShopMenuContract.View menuView;


    public ProductsAdapter(ProductsContract.View productsView, Context mContext, List<Product> products) {
        this.products = products;
        this.mContext = mContext;
        this.productsView = productsView;
    }


    public ProductsAdapter(ShopMenuContract.View menuView, Context mContext, List<Product> products) {
        this.products = products;
        this.mContext = mContext;
        this.menuView = menuView;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.product_list_item, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        final Product product = products.get(i) ;

        final int id = product.getId();

        final double price = product.getPrice();
        final String name = product.getName();
        final String brand = product.getBrand();
        final String amount = product.getAmount();
        final boolean inCart = product.isInCart();

        if (inCart){

            myViewHolder.linearLayout.setVisibility(View.VISIBLE);
            myViewHolder.addProduct.setVisibility(View.GONE);
            myViewHolder.constraintLayout.setBackgroundResource(R.drawable.border);
            final int cartCount = product.getCartCount();

            myViewHolder.count.setText(String.valueOf(cartCount));
        }

        final String image = APIUrl.Images_BASE_URL + product.getImage();

        Picasso.get()
                .load(image)
                .into(myViewHolder.image);


        myViewHolder.nameTv.setText(name);
        myViewHolder.brandTv.setText(brand);
        myViewHolder.amountTv.setText(amount);
        if (price != 0.0){
            myViewHolder.priceTv.setText("EGP" + price);
        }else {
            myViewHolder.priceTv.setVisibility(View.GONE);
            myViewHolder.addProduct.setText("ADD");
        }

/*
        if (product.getStock() > 0){
            myViewHolder.addProduct.setOnClickListener(new_icon View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myViewHolder.linearLayout.setVisibility(View.VISIBLE);
                    myViewHolder.addProduct.setVisibility(View.GONE);
                    myViewHolder.constraintLayout.setBackgroundResource(R.drawable.border);
                    mView.addToCart(product);
                }
            });
        }else {
            myViewHolder.addProduct.setBackgroundColor(mContext.getResources().getColor(R.color.red));
            myViewHolder.addProduct.setText("out of stock");
        }


*/

        myViewHolder.addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (price != 0.0){
                    menuView.showPopup(product.getId(), product.getPrice(), product.getStock());
                }else {

                    productsView.showPopup(product.getId());
                }
                //mView.addToCart(product);
            }
        });

    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTv, brandTv, amountTv, priceTv, addProduct, count;
        ImageView image, add, remove;

        ConstraintLayout constraintLayout;

        LinearLayout linearLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.item_name);
            brandTv = itemView.findViewById(R.id.item_brand);
            amountTv = itemView.findViewById(R.id.item_amount);
            priceTv = itemView.findViewById(R.id.item_price);
            image = itemView.findViewById(R.id.item_image);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            count = itemView.findViewById(R.id.item_count);

            addProduct = itemView.findViewById(R.id.add_to_cart);

            constraintLayout = itemView.findViewById(R.id.layout);

            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
