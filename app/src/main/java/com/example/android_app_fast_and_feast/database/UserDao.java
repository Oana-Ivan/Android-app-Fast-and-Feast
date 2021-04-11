package com.example.android_app_fast_and_feast.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_app_fast_and_feast.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users WHERE id IN (:userIds)")
    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM users WHERE username LIKE :username LIMIT 1")
    User findByUsername(String username);

    @Insert
    void insertAll(User... users);

    @Update
    public void update(User... users);

    @Delete
    void delete(User user);
}
