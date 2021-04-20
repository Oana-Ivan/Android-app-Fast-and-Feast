package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.UserDao;

public class Register extends AppCompatActivity {
    public static final String UserPREFERENCES = "UserPreferences";
    public static final String Username = "username";

    private TextView loginBtn;
    private CardView registerBtn;
    private EditText username;
    private EditText password;
    private EditText address;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (!MainActivity.isLoggedin) {
            loginBtn = (TextView) findViewById(R.id.logIn);
            registerBtn = (CardView) findViewById(R.id.register_card_view);
            username = (EditText) findViewById(R.id.register_username);
            password = (EditText) findViewById(R.id.register_password);
            address = (EditText) findViewById(R.id.register_address);
            phoneNumber = (EditText) findViewById(R.id.register_phone_number);

            loginBtn.setOnClickListener(v -> openLogIn());
            registerBtn.setOnClickListener(v -> openUserPage());
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void openLogIn(){
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }

    public void openUserPage(){
        if (!username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() &&
            !address.getText().toString().isEmpty()  && !phoneNumber.getText().toString().isEmpty()) {

            AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
            UserDao userDao = db.userDao();

            // verify if the user already exists
            if (userDao.findByUsername(username.getText().toString()) == null) {
                // insert new user
                User user = new User();
                user.setUsername(username.getText().toString());
                user.setPassword(password.getText().toString());
                user.setAddress(address.getText().toString());
                user.setPhoneNumber(phoneNumber.getText().toString());
                userDao.insertAll(user);

                SharedPreferences sharedpreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Username, user.getUsername());
                editor.apply();
                Toast.makeText(this, "Hello, " + sharedpreferences.getString(Username, ""), Toast.LENGTH_LONG).show();

                MainActivity.isLoggedin = true;

                finish();
                Intent intent = new Intent(this, UserPageActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Username taken", Toast.LENGTH_LONG).show();
                username.setText("");
            }
        }
        else {
            Toast.makeText(this, "All fields required!", Toast.LENGTH_LONG).show();
        }
    }

}