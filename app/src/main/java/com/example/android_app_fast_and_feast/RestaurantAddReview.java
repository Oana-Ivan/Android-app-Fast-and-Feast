package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

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
            Toast.makeText(RestaurantAddReview.this, "Your rating: " + ratingBar.getRating(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, RestaurantDetails.class);
            intent.putExtra("Restaurant", currentRestaurant);
            intent.putExtra("ReviewText", reviewText.getText());
            intent.putExtra("NoOfStars", ratingBar.getRating());
            startActivity(intent);
        });


    }
}