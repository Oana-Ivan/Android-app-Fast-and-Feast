package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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