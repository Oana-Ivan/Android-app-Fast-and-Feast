package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.firebasecode.Restaurant;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ImageView logoImage;
    private CardView seeRestaurantsBtn;
    private LinearLayout loginAndRegisterLL;
    private TextView loginBtn;
    private TextView registerBtn;
    private boolean insertIntoDatabase = false;
    DatabaseReference restaurantReff;

    String s1[], s2[], s3[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase connection Success", Toast.LENGTH_LONG).show();
        if(insertIntoDatabase){
            InsertIntoFirebase();
        }

        logoImage = (ImageView) findViewById(R.id.main_logo_image);
        seeRestaurantsBtn = (CardView) findViewById(R.id.main_btn_see_restaurants);
        loginBtn = (TextView) findViewById(R.id.main_login_btn);
        registerBtn = (TextView) findViewById(R.id.main_register_btn);
        loginAndRegisterLL = (LinearLayout) findViewById(R.id.main_login_and_register);

        // Add animation on logo image
        ObjectAnimator animationLogo = ObjectAnimator.ofFloat(logoImage, "translationX", 30f);
        animationLogo.setDuration(1000);
        animationLogo.start();

        // Add animation on see restaurants button
        ObjectAnimator animationRestaurantsButton = ObjectAnimator.ofFloat(seeRestaurantsBtn, "translationY", 30f);
        animationRestaurantsButton.setDuration(1000);
        animationRestaurantsButton.start();

        // Add animation on login/register
        ObjectAnimator animationloginAndRegister = ObjectAnimator.ofFloat(loginAndRegisterLL, "translationY", 30f);
        animationloginAndRegister.setDuration(1000);
        animationloginAndRegister.start();

        // Add setOnClickListeners for buttons
        loginBtn.setOnClickListener(r -> openActivity("login"));
        registerBtn.setOnClickListener(r -> openActivity("register"));
        seeRestaurantsBtn.setOnClickListener(r -> openActivity("restaurants"));
    }
    public void openActivity(String action){
        Intent intent;
        switch (action) {
            case "register": {
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
            }
            case "login": {
                intent = new Intent(this, LogIn.class);
                startActivity(intent);
                break;
            }

            case "restaurants": {
                intent = new Intent(this, RestaurantList.class);
                startActivity(intent);
            }
        }


    }
    public void InsertIntoFirebase(){
        restaurantReff = FirebaseDatabase.getInstance().getReference().child("Restaurant");
        s1 = getResources().getStringArray(R.array.restaurants);
        s2 = getResources().getStringArray(R.array.menus);
        s3 = getResources().getStringArray(R.array.descriptionFirebase);

        for(int i = 0 ; i < s1.length; i++){
            Restaurant restaurant = new Restaurant();
            restaurant.setName(s1[i]);
            restaurant.setMenu(s2[i*2],s3[i*2]);
            restaurant.setMenu(s2[i*2 + 1],s3[i*2 + 1]);
            restaurantReff.push().setValue(restaurant);

        }
        // bucata de cod pt inserarea unui nou restaurant
        /*
        Restaurant restaurant = new Restaurant();
        restaurant.setName(s1[i]);
        restaurant.setMenu(s2[i*2],s3[i*2]);
         */

        //Restaurant restaurant = new Restaurant();
    }
}