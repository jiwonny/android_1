package com.example.helloworld1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        TextView tvName;
        TextView tvPhone;

    public MyViewHolder(View view){
        super(view);

        ivPicture = view.findViewById(R.id.iv_picture);
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);

    }
}

    private ArrayList<FoodInfo> foodInfoArrayList;
    MyAdapter(ArrayList<FoodInfo> foodInfoAL){
        this.foodInfoArrayList = foodInfoAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;

        myViewHolder.ivPicture.setImageResource(foodInfoArrayList.get(position).drawableId);
        myViewHolder.tvName.setText(foodInfoArrayList.get(position).name);
        myViewHolder.tvPhone.setText(foodInfoArrayList.get(position).phone);
    }

    @Override
    public int getItemCount() {
        return foodInfoArrayList.size();
    }
}