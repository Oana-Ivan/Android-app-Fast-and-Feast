package com.example.android_app_fast_and_feast;

import java.io.Serializable;
import java.util.ArrayList;

public class MenuItem implements Serializable {
    private static Integer noOfMenus = 0;
    private Integer menuID;
    private String menuName;
    private ArrayList<String> menuContent;

    public MenuItem(String menuName, ArrayList<String> menuContent) {
        noOfMenus++;
        this.menuID = noOfMenus;
        this.menuName = menuName;
        this.menuContent = menuContent;
    }

    public Integer getMenuID() {
        return menuID;
    }

    public String getMenuName() {
        return menuName;
    }

    public ArrayList<String> getMenuContent() {
        return menuContent;
    }

    public String getMenuContentToString() {
        String content = "";
        for (int i = 0; i < menuContent.size(); i++) {
            content += "- " + menuContent.get(i) + "\n";
        }
        return content;
    }

    public void setMenuID(Integer menuID) {
        this.menuID = menuID;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuContent(ArrayList<String> menuContent) {
        this.menuContent = menuContent;
    }

}
