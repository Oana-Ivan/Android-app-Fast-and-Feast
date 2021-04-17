package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class MenusList extends AppCompatActivity {
    private RestaurantItem currentRestaurant;
    private TextView restaurantName;

    private RecyclerView menusListRV;
    private MenuAdapter menusListAdapter;
    private RecyclerView.LayoutManager menusListLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus_list);

        currentRestaurant = (RestaurantItem) getIntent().getSerializableExtra("Restaurant");

        restaurantName = findViewById(R.id.menus_restaurant_name);

        restaurantName.setText(currentRestaurant.getRestaurantName());

        ArrayList<MenuItem> menuItems = new ArrayList<>();
        ArrayList<String> content = new ArrayList<>();

        // TODO Replace menus
        content.add("Item1");
        content.add("Item2");
        menuItems.add(new MenuItem("Menu2", "item 1, item 2"));
        menuItems.add(new MenuItem("Menu1", "item 1, item 2"));
        menuItems.add(new MenuItem("Menu3", "item 1, item 2"));


        menusListRV = findViewById(R.id.menus_list_rv);
        menusListRV.setHasFixedSize(true);
        menusListLayoutManager = new LinearLayoutManager(this);
        menusListAdapter = new MenuAdapter(menuItems);

        menusListRV.setLayoutManager(menusListLayoutManager);
        menusListRV.setAdapter(menusListAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);
    }
}