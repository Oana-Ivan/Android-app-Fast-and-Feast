package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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

        imageView = findViewById(R.id.restaurants_image);
        imageView.setImageResource(R.mipmap.restaurant);

        ArrayList<RestaurantItem> restaurantItems = new ArrayList<>();
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_phone, "KFC", "fast food"));
        restaurantItems.add(new RestaurantItem(R.drawable.ic_action_user, "McDonalds", "fast food"));

        restaurantListRV = findViewById(R.id.restaurants_list_rv);
        restaurantListRV.setHasFixedSize(true);
        restaurantListLayoutManager = new LinearLayoutManager(this);
        restaurantListAdapter = new RestaurantAdapter(restaurantItems);

        restaurantListRV.setLayoutManager(restaurantListLayoutManager);
        restaurantListRV.setAdapter(restaurantListAdapter);
        restaurantListAdapter.setOnItemClickListener(position -> {
//                restaurantItems.get(position);
            Toast.makeText(RestaurantList.this, restaurantItems.get(position).getRestaurantName(), Toast.LENGTH_SHORT).show();
        });
    }
}