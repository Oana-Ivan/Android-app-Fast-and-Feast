package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Register extends AppCompatActivity {
    private TextView textViewRegister;
    private CardView register_Card_View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        textViewRegister = (TextView) findViewById(R.id.logIn);


        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogIn();
            }
            });
        register_Card_View = (CardView) findViewById(R.id.register_card_view);
        register_Card_View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserPage();
            }
        });

        }
        public void openLogIn(){
            Intent intent = new Intent(this, LogIn.class);
            startActivity(intent);
        }
        public void openUserPage(){
            Intent intent = new Intent(this, UserPageActivity.class);
            startActivity(intent);
        }

}