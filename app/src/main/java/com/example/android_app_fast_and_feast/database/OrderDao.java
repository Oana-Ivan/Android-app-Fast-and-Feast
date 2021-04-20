package com.example.android_app_fast_and_feast.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_app_fast_and_feast.Order;

import java.util.List;

@Dao
public interface OrderDao {
    @Query("SELECT * FROM orders")
    List<Order> getAll();

    @Query("SELECT count(*) FROM orders")
    int getNoOfOrders();

    @Query("SELECT * FROM orders WHERE id IN (:orderIds)")
    List<Order> loadAllByIds(int[] orderIds);

    @Query("SELECT * FROM orders WHERE order_number = (:orderNumber)")
    List<Order> getByOrderNumber(int orderNumber);

    @Query("SELECT * FROM orders WHERE username LIKE :username LIMIT 1")
    Order findByUsername(String username);

    @Insert
    void insertAll(Order... orders);

    @Update
    public void update(Order... orders);

    @Delete
    void delete(Order order);
}
