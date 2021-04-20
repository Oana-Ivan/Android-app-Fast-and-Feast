package com.example.android_app_fast_and_feast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.firebasecode.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class RestaurantList extends AppCompatActivity {
    private TextView Title;
    private ImageView imageView;

    private RecyclerView restaurantListRV;
    private RestaurantAdapter restaurantListAdapter;
    private RecyclerView.LayoutManager restaurantListLayoutManager;

    DatabaseReference reff;

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

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Restaurant restaurant = snapshot.getValue(Restaurant.class);

                    System.out.println(restaurant.getName());
                    restaurantItems.add(new RestaurantItem(R.drawable.restaurant_details_image, restaurant.getName(), "food"));
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //restaurantItems.add(new RestaurantItem(R.drawable.restaurant_details_image, "Test-2", "description-2"));

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

        // aici a facut prostii Bianca

        SharedPreferences sharedPref = getSharedPreferences("FastAndFeastApp", Context.MODE_PRIVATE);
        String valueState = sharedPref.getString("Restaurant","Not Active");

    }
}