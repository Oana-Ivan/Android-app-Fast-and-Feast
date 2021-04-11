package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.UserDao;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.PrimitiveIterator;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class UserPageActivity extends AppCompatActivity {
    CircleMenu user_page_circleMenu;
    RelativeLayout user_page_relative_layout;
    private TextView usernameTV;
    private TextView addressTV;
    private TextView phoneTV;

    private String username;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        SharedPreferences sharedpreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        username = sharedpreferences.getString(Username, "");
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        currentUser = userDao.findByUsername(username);

        usernameTV = findViewById(R.id.user_page_username);
        addressTV = findViewById(R.id.user_page_address);
        phoneTV = findViewById(R.id.user_page_phone);
        usernameTV.setText(currentUser.getUsername());
        addressTV.setText(currentUser.getAddress());
        phoneTV.setText(currentUser.getPhoneNumber());

        user_page_circleMenu = findViewById(R.id.user_page_circle_menu);
        user_page_relative_layout = findViewById(R.id.user_page_relative_layout);
        ManageCircleMenu();
    }
    public void ManageCircleMenu(){
        user_page_circleMenu.setMainMenu(Color.parseColor("#F4B872"),R.mipmap.hours,R.mipmap.close)
                .addSubMenu(Color.parseColor("#F4B872"),R.mipmap.restaurant)
                .addSubMenu(Color.parseColor("#F4B872"),R.mipmap.food_delivery)
                .addSubMenu(Color.parseColor("#F4B872"),R.mipmap.food_pack)
                .addSubMenu(Color.parseColor("#F4B872"),R.mipmap.settings)
                .addSubMenu(Color.parseColor("#F4B872"),R.mipmap.loupe)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int index) {
                        switch (index){
                            case 0:
                                Toast.makeText(UserPageActivity.this, "Restaurant", Toast.LENGTH_SHORT).show();
                                OpenActivity("Restaurant");
                                break;
                            case 1:
                                Toast.makeText(UserPageActivity.this, "My purchases", Toast.LENGTH_SHORT).show();
                                OpenActivity("History");
                                break;
                            case 2:
                                Toast.makeText(UserPageActivity.this, "Shopping Cart", Toast.LENGTH_SHORT).show();
                                OpenActivity("Shopping Cart");
                                break;
                            case 3:
                                Toast.makeText(UserPageActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                                break;
                            case 4:
                                Toast.makeText(UserPageActivity.this, "Search", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
    }
    public void OpenActivity(String action){
        Intent intent;
        switch (action) {
            case "Shopping Cart": {
                intent = new Intent(this, ShoppingCartActivity.class);
                startActivity(intent);
                break;
            }
            case "Restaurant": {
                intent = new Intent(this, RestaurantList.class);
                startActivity(intent);
                break;
            }
            case "History": {
                intent = new Intent(this, HistoryActivity.class);
                startActivity(intent);
                break;
            }

        }
    }
}