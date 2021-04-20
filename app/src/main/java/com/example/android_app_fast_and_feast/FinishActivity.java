package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android_app_fast_and_feast.Register.UserPREFERENCES;
import static com.example.android_app_fast_and_feast.Register.Username;
import android.widget.Toast;

import com.example.android_app_fast_and_feast.database.AppDatabase;
import com.example.android_app_fast_and_feast.database.HistoryOrdersDao;
import com.example.android_app_fast_and_feast.database.OrderDao;
import com.example.android_app_fast_and_feast.database.UserDao;

import java.util.ArrayList;
import java.util.List;

public class FinishActivity extends AppCompatActivity {

    private Button finish_Button;
    private EditText phoneET;
    private EditText addressET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_finish);
        finish_Button = (Button) findViewById(R.id.finish_button);
        finish_Button.setOnClickListener(v -> openActivity("Confirm"));

        phoneET = findViewById(R.id.register_phone_number);
        addressET = findViewById(R.id.editTextTextPersonName4);

        SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(Username, "login");
        TextView usernameTV = findViewById(R.id.username);
        usernameTV.setText(username);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
        UserDao userDao = db.userDao();
        User currentUser = userDao.findByUsername(username);

        phoneET.setText(currentUser.getPhoneNumber());
        addressET.setText(currentUser.getAddress());

    }
    public void openActivity(String action){
        Intent intent;
        switch (action) {
            case "Confirm": {
                //Toast.makeText(this, "Reminder", Toast.LENGTH_SHORT).show();
                Intent reminderIntent = new Intent(FinishActivity.this, ReminderRestaurant.class);
                PendingIntent pendingReminderIntent = PendingIntent.getBroadcast(FinishActivity.this, 0, reminderIntent,0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

                long timeAtButtonClicked = System.currentTimeMillis();

                long tenSecondsInMillis = 1000 * 10;

                alarmManager.set(AlarmManager.RTC_WAKEUP,
                        timeAtButtonClicked + tenSecondsInMillis,
                        pendingReminderIntent
                        );

                SharedPreferences sharedPreferences = getSharedPreferences(UserPREFERENCES, Context.MODE_PRIVATE);
                String username = sharedPreferences.getString(Username, "");

                AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-app").allowMainThreadQueries().build();
                UserDao userDao = db.userDao();
                User currentUser = userDao.findByUsername(username);

                OrderDao orderDao = db.orderDao();
                List<Order> currentOrder = new ArrayList<Order>();
                for (int i = 0; i < orderDao.getByOrderNumber(LogIn.orderNumber).size(); i++) {
                    currentOrder.add(orderDao.getByOrderNumber(LogIn.orderNumber).get(i));
                }

                // insert in history_orders
                HistoryOrdersDao historyOrdersDao = db.historyOrdersDao();
                for (int i = 0; i < currentOrder.size(); i++) {
                    HistoryOrders historyOrders = new HistoryOrders();
                    historyOrders.setMenuName(currentOrder.get(i).getMenuName());
                    historyOrders.setUsername(currentOrder.get(i).getUsername());
                    historyOrders.setOrderNumber(currentOrder.get(i).getOrderNumber());
                    historyOrdersDao.insertAll(historyOrders);
                    // delete from orders
                    orderDao.delete(currentOrder.get(i));
                }

                finish();
                intent = new Intent(this, ConfirmOrder.class);
                startActivity(intent);
                break;
            }
        }
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = "RestaurantReminderChannel";
            String description = "Channel for restaurant reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyRestaurants", name, importance);

            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }
}