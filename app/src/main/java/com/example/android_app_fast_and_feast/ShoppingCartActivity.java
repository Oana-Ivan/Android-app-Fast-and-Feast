package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza};
    private Button finish_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        recyclerView = findViewById(R.id.ShoppingCart_RecicleView);

        s1 = getResources().getStringArray(R.array.products);
        s2 = getResources().getStringArray(R.array.description);

        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this, s1, s2 , images);
        recyclerView.setAdapter(shoppingCartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        finish_Button = (Button) findViewById(R.id.finish_button);
        finish_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity("Finish");
            }

        });
    }

    public void openActivity(String action){
        Intent intent;
        switch (action) {
            case "Finish": {
                intent = new Intent(this, FinishActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}