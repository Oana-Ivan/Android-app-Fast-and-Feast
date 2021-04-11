package com.example.android_app_fast_and_feast.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_app_fast_and_feast.RestaurantItem;

import java.util.List;

@Dao
public interface RestaurantDao {
    @Query("SELECT * FROM restaurants")
    List<RestaurantItem> getAll();

    @Query("SELECT * FROM restaurants WHERE restaurant_id IN (:restaurantIds)")
    List<RestaurantItem> loadAllByIds(int[] restaurantIds);

    @Query("SELECT * FROM restaurants WHERE restaurant_name LIKE :restaurantName LIMIT 1")
    RestaurantItem findByName(String restaurantName);

    @Insert
    void insertAll(RestaurantItem... restaurantItems);

    @Update
    public void update(RestaurantItem... restaurantItems);

    @Delete
    void delete(RestaurantItem restaurantItem);
}
