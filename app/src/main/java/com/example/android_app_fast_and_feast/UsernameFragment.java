package com.example.android_app_fast_and_feast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;


public class UsernameFragment extends Fragment {
    private String username;
    private TextView usernameTV;

    public UsernameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_username, container, false);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(Username, "");
        usernameTV = getActivity().findViewById(R.id.username);
//        usernameTV.setText(username);

        v.setOnClickListener(v1 -> {
            // redirect to user page activity
            Intent intent = new Intent(getContext(), UserPageActivity.class);
            startActivity(intent);
        });
        return v;
    }
}