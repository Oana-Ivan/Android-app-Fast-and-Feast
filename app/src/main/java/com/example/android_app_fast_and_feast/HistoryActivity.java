package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.HistoryOrdersDao;
import com.example.android_app_fast_and_feast.database.OrderDao;

import java.util.ArrayList;
import java.util.List;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<String> s1, s2;
    List<Integer> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.History_RecicleView);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "login");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);

        s1 = new ArrayList<String>();
        s2 = new ArrayList<String>();
        images = new ArrayList<Integer>();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
        HistoryOrdersDao historyOrdersDao = db.historyOrdersDao();

        List<Order> currentOrder = new ArrayList<Order>();
        for (int i = 0; i < historyOrdersDao.findByUsernameAll(username).size(); i++) {
            HistoryOrders currentHistoryOrder = historyOrdersDao.findByUsernameAll(username).get(i);
            s1.add(currentHistoryOrder.getMenuName());
            s2.add("  ");
            images.add(R.mipmap.pizza);
        }

        HistoryAdapter HistoryAdapter = new HistoryAdapter(s1, s2 , images, this);
        recyclerView.setAdapter(HistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}