package com.example.android_app_fast_and_feast.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.android_app_fast_and_feast.MenuItem;

import java.util.List;

@Dao
public interface MenuDao {
    @Query("SELECT * FROM menus")
    List<MenuItem> getAll();

    @Query("SELECT * FROM menus WHERE menuID IN (:menuIds)")
    List<MenuItem> loadAllByIds(int[] menuIds);

    @Query("SELECT * FROM menus WHERE menu_name LIKE :menuName LIMIT 1")
    MenuItem findByUsername(String menuName);

    @Insert
    void insertAll(MenuItem... menuItems);

    @Update
    public void update(MenuItem... menuItems);

    @Delete
    void delete(MenuItem menuItem);
}
