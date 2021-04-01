package com.example.android_app_fast_and_feast;

import java.io.Serializable;

public class RestaurantItem implements Serializable {
    private int restaurantImage;
    private String restaurantName;
    private String restaurantDescription;

    public RestaurantItem(int restaurantImage, String restaurantName, String restaurantDescription) {
        this.restaurantImage = restaurantImage;
        this.restaurantName = restaurantName;
        this.restaurantDescription = restaurantDescription;
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
