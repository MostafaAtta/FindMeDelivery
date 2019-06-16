package com.atta.findmedelivery.model;

import com.google.gson.annotations.SerializedName;

public class ShopResult {
    @SerializedName("error")
    private Boolean error;

    @SerializedName("shop")
    private Shop shop;

    public ShopResult(Boolean error, Shop shop) {
        this.error = error;
        this.shop = shop;
    }

    public Boolean getError() {
        return error;
    }

    public Shop getShop() {
        return shop;
    }
}
