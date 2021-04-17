package com.example.android_app_fast_and_feast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FinishActivity extends AppCompatActivity {

    private Button finish_Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.activity_finish);
        finish_Button = (Button) findViewById(R.id.finish_button);
        finish_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openActivity("Confirm");
            }

        });
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