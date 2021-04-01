package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantDetails extends AppCompatActivity {
    private RestaurantItem currentRestaurant;
    private TextView restaurantName;
    private TextView restaurantDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        currentRestaurant = (RestaurantItem) getIntent().getSerializableExtra("Restaurant");
        restaurantName = findViewById(R.id.restaurant_details_name);
        restaurantDescription = findViewById(R.id.restaurant_details_description);

        restaurantName.setText(currentRestaurant.getRestaurantName());
        restaurantDescription.setText(currentRestaurant.getRestaurantDescription());
    }
}