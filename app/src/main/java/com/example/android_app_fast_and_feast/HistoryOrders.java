package com.example.android_app_fast_and_feast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_orders")
public class HistoryOrders {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "menu_name")
    private String menuName;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "order_number")
    private int orderNumber;

    public int getId() {
        return id;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getUsername() {
        return username;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
