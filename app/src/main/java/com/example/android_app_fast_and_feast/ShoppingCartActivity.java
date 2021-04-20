package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.OrderDao;

import java.util.ArrayList;
import java.util.List;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    List<String> s1, s2;
    List<Integer> images; // = {R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza};
    private Button finish_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "login");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);

        recyclerView = findViewById(R.id.ShoppingCart_RecicleView);

//        s1 = getResources().getStringArray(R.array.products);
//        s2 = getResources().getStringArray(R.array.description);

        s1 = new ArrayList<String>();
        s2 = new ArrayList<String>();
        images = new ArrayList<Integer>();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
        OrderDao orderDao = db.orderDao();
        List<Order> currentOrder = new ArrayList<Order>();
        for (int i = 0; i < orderDao.getByOrderNumber(LogIn.orderNumber).size(); i++) {
            currentOrder.add(orderDao.getByOrderNumber(LogIn.orderNumber).get(i));
        }

        for (int i = 0; i < currentOrder.size(); i++) {
            s1.add(currentOrder.get(i).getMenuName());
            s2.add("  ");
            images.add(R.mipmap.pizza);
        }

        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(s1, s2 , images, this);
        recyclerView.setAdapter(shoppingCartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        finish_Button = (Button) findViewById(R.id.finish_button);
        finish_Button.setOnClickListener(v -> openActivity("Finish"));
    }

    public void openActivity(String action){
        Intent intent;
        switch (action) {
            case "Finish": {
                finish();
                intent = new Intent(this, FinishActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}