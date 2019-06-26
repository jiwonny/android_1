package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<FoodInfo> foodInfoArrayList = new ArrayList<>();
        foodInfoArrayList.add(new FoodInfo(R.drawable.a_icon, "김현석", "010-0000-0000"));
        foodInfoArrayList.add(new FoodInfo(R.drawable.b_icon, "이현석", "010-1111-1111"));
        foodInfoArrayList.add(new FoodInfo(R.drawable.c_icon, "양현석", "010-2222-2222"));

        MyAdapter myAdapter = new MyAdapter(foodInfoArrayList);

        mRecyclerView.setAdapter(myAdapter);

    }
}
