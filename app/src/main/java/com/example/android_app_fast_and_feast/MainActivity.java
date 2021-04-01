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

public class MainActivity extends AppCompatActivity {
    private ImageView logoImage;
    private CardView seeRestaurantsBtn;
    private LinearLayout loginAndRegisterLL;
    private TextView loginBtn;
    private TextView registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}