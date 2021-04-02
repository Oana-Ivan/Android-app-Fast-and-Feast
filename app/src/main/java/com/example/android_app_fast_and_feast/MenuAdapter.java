package com.example.android_app_fast_and_feast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{
    private ArrayList<MenuItem> menuItems;

    public MenuAdapter(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        MenuAdapter.MenuViewHolder menuViewHolder = new MenuAdapter.MenuViewHolder(view);
        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem currentMenu = menuItems.get(position);

        holder.menuName.setText(currentMenu.getMenuName());
        holder.menuContent.setText(currentMenu.getMenuContentToString());
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder {
        public TextView menuName;
        public TextView menuContent;
        public Button addMenu;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.menu_name);
            menuContent = itemView.findViewById(R.id.menu_content);
            addMenu = itemView.findViewById(R.id.menu_add_btn);

            addMenu.setOnClickListener(m -> {
                Toast.makeText(itemView.getContext(), "Added " + menuName.getText() + " to shopping cart", Toast.LENGTH_SHORT).show();
                //TODO Add menu to order
            });

        }
    }
}