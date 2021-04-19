package com.example.android_app_fast_and_feast.firebasecode;

public class Menu {
    private String Name;
    private String Description;

    public Menu(String name, String description){
        Name = name;
        Description = description;
    }
    public Menu(){
        Name = "Empty";
        Description = "Empty";
    }
    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setName(String name) {
        Name = name;
    }
}
