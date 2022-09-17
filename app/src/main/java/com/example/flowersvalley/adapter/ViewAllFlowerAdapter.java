package com.example.flowersvalley.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowersvalley.R;
import com.example.flowersvalley.model.Flower;

import java.util.ArrayList;

/**
 * Created by AuthSafe on 17-09-2022.
 * <br>
 * Copyright (c) 2022 SecureLayer7 Technologies. All rights reserved.
 */
public class ViewAllFlowerAdapter extends RecyclerView.Adapter<ViewAllFlowerAdapter.ViewHolder> {

    ArrayList<Flower> list;
    Context context;

    public ViewAllFlowerAdapter(ArrayList<Flower> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_all_flower_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flower flower = list.get(position);
        holder.flowerName.setText("" + flower.getName());
        holder.flowerPrice.setText("" + flower.getPrice());


        Glide.with(context)
                .load(flower.getImageUrl())
                .into(holder.flowerImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView flowerName, flowerPrice;
        AppCompatImageView flowerImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flowerName = itemView.findViewById(R.id.flower_name);
            flowerPrice = itemView.findViewById(R.id.flower_price);
            flowerImage = itemView.findViewById(R.id.flower_image);
        }
    }
}
