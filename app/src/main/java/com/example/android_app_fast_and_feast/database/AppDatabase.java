package com.example.android_app_fast_and_feast.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_app_fast_and_feast.MenuItem;
import com.example.android_app_fast_and_feast.RestaurantItem;
import com.example.android_app_fast_and_feast.User;

@Database(entities = {User.class, RestaurantItem.class, MenuItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RestaurantDao restaurantDao();
    public abstract MenuDao menuDao();

}