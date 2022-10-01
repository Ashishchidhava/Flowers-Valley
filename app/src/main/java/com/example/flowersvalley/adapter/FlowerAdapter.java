package com.example.flowersvalley.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flowersvalley.BottomMenuHelper;
import com.example.flowersvalley.MainActivity;
import com.example.flowersvalley.R;
import com.example.flowersvalley.SharedPreferenceManager;
import com.example.flowersvalley.Utils;
import com.example.flowersvalley.fragment.FlowerDetailFragment;
import com.example.flowersvalley.model.Flower;

import java.util.ArrayList;

public class FlowerAdapter extends RecyclerView.Adapter<FlowerAdapter.ViewHolder> {
    SharedPreferenceManager sharedPreferenceManager;
    ArrayList<Flower> list;
    Context context;
    int count = 1;

    public FlowerAdapter(ArrayList<Flower> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_card_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Flower flower = list.get(position);
        holder.flowerName.setText(flower.getFlowerName());
        holder.flowerPrice.setText(flower.getFlowerPrice());
        Glide.with(context)
                .load(flower.getFlowerImageUrl())
                .into(holder.flowerImage);

        sharedPreferenceManager = new SharedPreferenceManager(context);

        holder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferenceManager.setItemCounter(count++);
                BottomMenuHelper.showBadge(context, MainActivity.bottomNavigationView, R.id.nav_cart, "" + sharedPreferenceManager.getItemCounter());
            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FlowerDetailFragment fragment = new FlowerDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putString("flower_id", flower.getFlowerId());
                fragment.setArguments(bundle);
                Utils.replaceFragment(fragment, (FragmentActivity) context);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView flowerName, flowerPrice;
        AppCompatImageView flowerImage;
        CardView cardView;
        AppCompatImageButton addItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            flowerName = itemView.findViewById(R.id.flower_name);
            flowerPrice = itemView.findViewById(R.id.flower_price);
            flowerImage = itemView.findViewById(R.id.flower_image);
            addItem = itemView.findViewById(R.id.btn_add_item);

        }
    }
}
