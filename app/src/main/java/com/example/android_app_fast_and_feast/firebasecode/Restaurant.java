package com.example.android_app_fast_and_feast.firebasecode;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String Name;
    private List<Menu> Menus = new ArrayList<Menu>();

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public List<Menu> getMenu() {
        return Menus;
    }

    public void setMenu(String name, String description) {
        Menu menu = new Menu(name, description);
        Menus.add(menu);
    }

}
