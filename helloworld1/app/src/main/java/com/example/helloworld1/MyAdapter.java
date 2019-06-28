package com.example.helloworld1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPicture;
        TextView tvName;
        TextView tvPhone;
        LinearLayout parentLayout;

    public MyViewHolder(View view){
        super(view);

        //ivPicture = view.findViewById(R.id.iv_picture);
        tvName = view.findViewById(R.id.tv_name);
        //tvPhone = view.findViewById(R.id.tv_phone);
        parentLayout = view.findViewById(R.id.linearLayout);

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

        //myViewHolder.ivPicture.setImageResource(foodInfoArrayList.get(position).drawableId);
        myViewHolder.tvName.setText(foodInfoArrayList.get(position).name);
        //myViewHolder.tvPhone.setText(foodInfoArrayList.get(position).phone);
        final String Name = foodInfoArrayList.get(position).name;
        final String Ph_number = foodInfoArrayList.get(position).phone;
        final long ID = foodInfoArrayList.get(position).id;
        final long Image_ID = foodInfoArrayList.get(position).image_id;
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "전화걸기", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(), subactivity_contact.class);
                i.putExtra("name", Name);
                i.putExtra("ph_num", Ph_number);
                i.putExtra("id", ID);
                i.putExtra("image_id", Image_ID);
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodInfoArrayList.size();
    }
}