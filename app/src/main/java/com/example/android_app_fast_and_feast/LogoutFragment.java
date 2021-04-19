package com.example.android_app_fast_and_feast;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;


public class LogoutFragment extends Fragment {

    public LogoutFragment() {
        super(R.layout.fragment_logout);
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_logout, container, false);
        v.setOnClickListener(v1 -> {
            // delete username from shared preferences
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            // redirect to main activity
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
        });
        return v;
    }
}