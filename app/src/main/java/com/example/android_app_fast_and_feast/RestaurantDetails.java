package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class RestaurantDetails extends AppCompatActivity {
    private RestaurantItem currentRestaurant;
    private TextView restaurantName;
    private TextView restaurantDescription;
    private CardView seeMenus;
    private CardView addReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_details);

        currentRestaurant = (RestaurantItem) getIntent().getSerializableExtra("Restaurant");
        restaurantName = findViewById(R.id.restaurant_details_name);
        restaurantDescription = findViewById(R.id.restaurant_details_description);
        seeMenus = findViewById(R.id.restaurant_details_menus);
        addReview = findViewById(R.id.restaurant_details_add_review);

        restaurantName.setText(currentRestaurant.getRestaurantName());
        restaurantDescription.setText(currentRestaurant.getRestaurantDescription());

        seeMenus.setOnClickListener(r -> openActivity("menus"));
        addReview.setOnClickListener(r -> openActivity("add_review"));

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "login");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);
    }

    private void openActivity(String action) {
        Intent intent;
        switch (action) {
            case "menus": {
                intent = new Intent(this, MenusList.class);
                intent.putExtra("Restaurant", currentRestaurant);
                startActivity(intent);
                break;
            }
            case "add_review": {
                intent = new Intent(this, RestaurantAddReview.class);
                intent.putExtra("Restaurant", currentRestaurant);
                startActivity(intent);
                break;
            }
        }

    }
}