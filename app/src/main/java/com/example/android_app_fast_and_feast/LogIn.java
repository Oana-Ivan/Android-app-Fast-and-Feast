package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.OrderDao;
import com.example.android_app_fast_and_feast.database.UserDao;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;

public class LogIn extends AppCompatActivity {
    private TextView registerBtn;
    private EditText usernameET;
    private EditText passwordET;
    private CardView loginBtn;
    public static int orderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        if (!MainActivity.isLoggedin) {
            registerBtn = (TextView) findViewById(R.id.register);
            loginBtn = findViewById(R.id.login_btn_user_page);
            usernameET = findViewById(R.id.login_username);
            passwordET = findViewById(R.id.login_password);

            // redirect to Register
            registerBtn.setOnClickListener(v -> openActivity("register"));

            loginBtn.setOnClickListener(v -> {
                // verify credentials
                if (!usernameET.getText().toString().isEmpty() && !passwordET.getText().toString().isEmpty()) {
                    verifyUser(usernameET.getText().toString(), passwordET.getText().toString());
                }
                else {
                    Toast.makeText(this, "All fields required!", Toast.LENGTH_LONG).show();
                }
            });
        }
        else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void verifyUser(String username, String password) {
        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();

        User currentUser = userDao.findByUsername(username);

        if (currentUser != null) {
            if (currentUser.getPassword().equals(password)) {
                SharedPreferences sharedpreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(Username, currentUser.getUsername());
                editor.apply();

                OrderDao orderDao = db.orderDao();
                orderNumber = orderDao.getNoOfOrders() + 1;

                MainActivity.isLoggedin = true;

                Toast.makeText(this, "Hello, " + sharedpreferences.getString(Username, ""), Toast.LENGTH_LONG).show();
                finish();
                openActivity("user_activity");
            }
            else {
                Toast.makeText(this, "Wrong password", Toast.LENGTH_LONG).show();
                passwordET.setText("");
            }
        }
        else {
            Toast.makeText(this, "Username not found", Toast.LENGTH_LONG).show();
            usernameET.setText("");
            passwordET.setText("");
        }
    }

    public void openActivity(String action) {
        Intent intent;
        switch (action) {
            case "register": {
                intent = new Intent(this, Register.class);
                startActivity(intent);
                break;
            }
            case "user_activity": {
                intent = new Intent(this, UserPageActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}