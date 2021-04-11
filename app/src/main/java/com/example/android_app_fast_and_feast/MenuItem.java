package com.example.android_app_fast_and_feast;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "menus")
public class MenuItem implements Serializable {
//    private static Integer noOfMenus = 0;
    @PrimaryKey
    private Integer menuID;
    @ColumnInfo(name = "menu_name")
    private String menuName;
    @ColumnInfo(name = "menu_content")
    private String menuContent;
    // TODO ONE TO MANY: restaurant - menu
    // TODO MANY TO MANY: user - menu (orders)
//    @ForeignKey(entity = RestaurantItem.class, parentColumns = restaurantID, childColumns = )
//    private Integer restaurantID;


    public MenuItem() {
    }

    @Ignore
    public MenuItem(String menuName, String menuContent) {
//        noOfMenus++;
//        this.menuID = noOfMenus;
//        this.menuID = menuID;
        this.menuName = menuName;
        this.menuContent = menuContent;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public String getMenuContent() {
        return menuContent;
    }

//    public String getMenuContentToString() {
//        String content = "";
//        for (int i = 0; i < menuContent.size(); i++) {
//            content += "- " + menuContent.get(i) + "\n";
//        }
//        return content;
//    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuContent(String menuContent) {
        this.menuContent = menuContent;
    }

}
