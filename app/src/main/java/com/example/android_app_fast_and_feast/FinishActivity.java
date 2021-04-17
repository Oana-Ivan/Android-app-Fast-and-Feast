package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class FinishActivity extends AppCompatActivity {

    private Button finish_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        finish_Button = (Button) findViewById(R.id.finish_button);
        finish_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity("Confirm");
            }

        });

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);
    }
    public void openActivity(String action){
        Intent intent;
        switch (action) {
            case "Confirm": {
                intent = new Intent(this, ConfirmOrder.class);
                startActivity(intent);
                break;
            }
        }
    }
}