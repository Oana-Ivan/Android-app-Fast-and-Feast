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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.firebasecode.Menu;
import com.example.android_app_fast_and_feast.firebasecode.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class MenusList extends AppCompatActivity {
    private RestaurantItem currentRestaurant;
    private TextView restaurantName;
    DatabaseReference reff;

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
        //Bianca

        reff = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    int ok = 0;
                    Restaurant restaurant = snapshot.getValue(Restaurant.class);
                    //System.out.println(restaurant.getName());
                    Log.d("myTag", "fdgdf");
                    if(restaurant.getName().contentEquals(currentRestaurant.getRestaurantName())){
                       DataSnapshot menureff = snapshot.child("menu");
                        ok = 1;
                       for(DataSnapshot snapshotMenu : menureff.getChildren()){
                           Menu m = snapshotMenu.getValue(Menu.class);

                           menuItems.add(new MenuItem(m.getName(), m.getDescription()));

                       }
                       if(ok == 1){
                            break;
                        }
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //


        // TODO Replace menus
        content.add("Item1");
        content.add("Item2");
       // menuItems.add(new MenuItem("Menu2", "item 1, item 2"));

       // menuItems.add(new MenuItem("Menu3", "item 1, item 2"));


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