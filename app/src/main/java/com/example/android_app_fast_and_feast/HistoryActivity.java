package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class HistoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String s1[], s2[];
    int images[] = {R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza,R.mipmap.pizza};
    private Button finish_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.History_RecicleView);

        s1 = getResources().getStringArray(R.array.products);
        s2 = getResources().getStringArray(R.array.description);

        HistoryAdapter HistoryAdapter = new HistoryAdapter(this, s1, s2 , images);
        recyclerView.setAdapter(HistoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);
    }
}