package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

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
    }
}