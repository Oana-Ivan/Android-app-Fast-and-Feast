package com.example.android_app_fast_and_feast.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android_app_fast_and_feast.HistoryOrders;
import com.example.android_app_fast_and_feast.MenuItem;
import com.example.android_app_fast_and_feast.Order;
import com.example.android_app_fast_and_feast.RestaurantItem;
import com.example.android_app_fast_and_feast.User;

@Database(entities = {User.class, RestaurantItem.class, MenuItem.class, Order.class, HistoryOrders.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RestaurantDao restaurantDao();
    public abstract MenuDao menuDao();
    public abstract OrderDao orderDao();
    public abstract HistoryOrdersDao historyOrdersDao();
}