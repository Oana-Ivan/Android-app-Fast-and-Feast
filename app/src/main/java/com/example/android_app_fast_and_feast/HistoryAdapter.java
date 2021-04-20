package com.example.android_app_fast_and_feast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder>{

    List<String> data1, data2;
    List<Integer> images;
    Context context;

    public HistoryAdapter(List<String> data1, List<String> data2, List<Integer> images, Context context) {
        this.data1 = data1;
        this.data2 = data2;
        this.images = images;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.shopping_cart_row, parent, false);
        return new HistoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.MyViewHolder holder, int position) {
        holder.shoppingCartText1.setText(data1.get(position));
        holder.shoppingCartText2.setText(data2.get(position));
        holder.shoppingCartImage.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView shoppingCartText1, shoppingCartText2;
        ImageView shoppingCartImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingCartText1 = itemView.findViewById(R.id.shopping_cart_row_name_txt);
            shoppingCartText2 = itemView.findViewById(R.id.shopping_cart_row_description_txt);
            shoppingCartImage = itemView.findViewById(R.id.shopping_cart_row_image_view);
        }
    }
}
