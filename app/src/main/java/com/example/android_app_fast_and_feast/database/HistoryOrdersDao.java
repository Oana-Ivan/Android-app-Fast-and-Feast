package com.example.android_app_fast_and_feast.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_app_fast_and_feast.HistoryOrders;
import com.example.android_app_fast_and_feast.Order;

import java.util.List;

@Dao
public interface HistoryOrdersDao {
    @Query("SELECT * FROM history_orders")
    List<HistoryOrders> getAll();

    @Query("SELECT count(*) FROM history_orders")
    int getNoOfHistoryOrders();

    @Query("SELECT * FROM history_orders WHERE id IN (:orderIds)")
    List<HistoryOrders> loadAllByIds(int[] orderIds);

    @Query("SELECT * FROM history_orders WHERE order_number = (:orderNumber)")
    List<HistoryOrders> getByHistoryOrdersNumber(int orderNumber);

    @Query("SELECT * FROM history_orders WHERE username LIKE :username")
    List<HistoryOrders> findByUsernameAll(String username);

    @Query("SELECT * FROM history_orders WHERE username LIKE :username LIMIT 1")
    HistoryOrders findByUsername(String username);

    @Insert
    void insertAll(HistoryOrders... orders);

    @Update
    public void update(HistoryOrders... orders);

    @Delete
    void delete(HistoryOrders order);
}
