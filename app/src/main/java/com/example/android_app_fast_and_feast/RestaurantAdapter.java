package com.example.android_app_fast_and_feast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {
    private ArrayList<RestaurantItem> restaurantItems;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public RestaurantAdapter(ArrayList<RestaurantItem> restaurantItems) {
        this.restaurantItems = restaurantItems;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item, parent, false);
         RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view, onItemClickListener);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        RestaurantItem currentRestaurant = restaurantItems.get(position);

        holder.restaurantImage.setImageResource(currentRestaurant.getRestaurantImage());
        holder.restaurantName.setText(currentRestaurant.getRestaurantName());
        holder.restaurantDescription.setText(currentRestaurant.getRestaurantDescription());
    }

    @Override
    public int getItemCount() {
        return restaurantItems.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        public ImageView restaurantImage;
        public TextView restaurantName;
        public TextView restaurantDescription;

        public RestaurantViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.restaurant_image);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantDescription = itemView.findViewById(R.id.restaurant_description);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }
}
