package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class RestaurantList extends AppCompatActivity {
    private TextView Title;
    private ImageView imageView;

    private RecyclerView restaurantListRV;
    private RestaurantAdapter restaurantListAdapter;
    private RecyclerView.LayoutManager restaurantListLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);

        imageView = findViewById(R.id.restaurants_image);
        imageView.setImageResource(R.mipmap.restaurant);

        ArrayList<RestaurantItem> restaurantItems = new ArrayList<>();
        restaurantItems.add(new RestaurantItem(R.drawable.restaurant_details_image, "Test-1", "description-1"));
        restaurantItems.add(new RestaurantItem(R.drawable.restaurant_details_image, "Test-2", "description-2"));

        restaurantListRV = findViewById(R.id.restaurants_list_rv);
        restaurantListRV.setHasFixedSize(true);
        restaurantListLayoutManager = new LinearLayoutManager(this);
        restaurantListAdapter = new RestaurantAdapter(restaurantItems);

        restaurantListRV.setLayoutManager(restaurantListLayoutManager);
        restaurantListRV.setAdapter(restaurantListAdapter);
        restaurantListAdapter.setOnItemClickListener(position -> {
            Toast.makeText(RestaurantList.this, restaurantItems.get(position).getRestaurantName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, RestaurantDetails.class);
            intent.putExtra("Restaurant", restaurantItems.get(position));
            startActivity(intent);
        });
    }
}