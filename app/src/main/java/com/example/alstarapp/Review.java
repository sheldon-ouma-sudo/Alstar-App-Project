package com.example.alstarapp;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Review")
public class Review extends ParseObject {
    //define the objects on the parse dash board on our class
    public static final String KEY_PRODUCT_TYPE = "productType";
    public static final String KEY_ITEM_NAME = "productName";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_STORE_NAME = "storeName";
    public static final String KEY_PURCHASE_MODE = "PurchaseMode";
    public static final String KEY_PRICE = "price";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_RATING = "productReview";
    public static final String KEY_REVIEWER = "Reviewer";


    //Let us define the getters and setters
    public String getStoreName() {
        return getString(KEY_STORE_NAME);
    }

    public void setKeyStoreName(String storeName) {
        put(KEY_STORE_NAME, storeName);
    }

    public String getKeyBrand() {
        return getString(KEY_BRAND);
    }

    public void setKeyBrand(String brand) {
        put(KEY_BRAND, brand);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public String getKeyDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setKeyDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public int getPrice() {
        return getInt(KEY_PRICE);
    }

    public void setKeyPrice(int price) {
        put(KEY_PRICE, price);
    }

    public ParseUser getReviewer() {
        return getParseUser(KEY_REVIEWER);
    }

    public void setKeyReviewer(ParseUser reviewer) {
        put(KEY_REVIEWER, reviewer);
    }

    public String getKeyProductType(String productType) {
        return getString(KEY_PRODUCT_TYPE);
    }

    public void setKeyProductType(String productType) {
        put(KEY_PRODUCT_TYPE, productType);
    }

    public String getKeyPurchaseMode() {
        return getString(KEY_PURCHASE_MODE);
    }

    public void setKeyPurchaseMode(String PurchaseMode) {
        put(KEY_PURCHASE_MODE, PurchaseMode);
    }

    public float getItemCount() {
        return getLong(KEY_RATING);
    }

    public void setKeyRating(Float rating) {
        put(KEY_RATING, rating);
    }

    public String getKeyItemName() {
        return getString(KEY_ITEM_NAME);
    }

    public void setKeyItemName(String itemName) {
        put(KEY_ITEM_NAME, itemName);
    }

}