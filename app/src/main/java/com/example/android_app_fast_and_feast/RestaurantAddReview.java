package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class RestaurantAddReview extends AppCompatActivity {
    RestaurantItem currentRestaurant;
    private RatingBar ratingBar;
    private EditText reviewText;
    private Button sendReviewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_add_review);

        currentRestaurant = (RestaurantItem) getIntent().getSerializableExtra("Restaurant");

        ratingBar = findViewById(R.id.restaurant_review_rating);
        reviewText = findViewById(R.id.restaurant_write_review);
        sendReviewBtn = findViewById(R.id.restaurant_add_review_btn);

        sendReviewBtn.setOnClickListener(v -> {
            // TODO Get rating from restaurant review
            SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
            String username = sharedPreferences.getString(Username, "");
            if (username.isEmpty()) {
                Toast.makeText(RestaurantAddReview.this, "You are not logged in", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(RestaurantAddReview.this, "Your rating: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, RestaurantDetails.class);
                intent.putExtra("Restaurant", currentRestaurant);
                intent.putExtra("ReviewText", reviewText.getText());
                intent.putExtra("NoOfStars", ratingBar.getRating());
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);
    }
}