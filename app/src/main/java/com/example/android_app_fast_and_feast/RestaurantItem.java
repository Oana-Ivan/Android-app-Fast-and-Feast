package com.example.android_app_fast_and_feast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "restaurants")
public class RestaurantItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int restaurant_id;
    @ColumnInfo(name = "restaurant_image")
    private int restaurantImage;
    @ColumnInfo(name = "restaurant_name")
    private String restaurantName;
    @ColumnInfo(name = "restaurant_description")
    private String restaurantDescription;

    public RestaurantItem() {
    }

    @Ignore
    public RestaurantItem(int restaurantImage, String restaurantName, String restaurantDescription) {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setRestaurantDescription(String restaurantDescription) {
        this.restaurantDescription = restaurantDescription;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantDescription() {
        return restaurantDescription;
    }
}
