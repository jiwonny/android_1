package com.example.helloworld1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {

    public Fragment1(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);



        ArrayList<FoodInfo> foodInfoArrayList = new ArrayList<>();
        String address =
                "[{'name':'김현석', 'phone':'010-0000-0000'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'양현석', 'phone':'010-2222-2222'}]";

        try {
            JSONArray jarray = new JSONArray(address);   // JSONArray 생성
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String phone = jObject.getString("phone");
                String name = jObject.getString("name");
                foodInfoArrayList.add(new FoodInfo(R.drawable.a_icon, name, phone));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyAdapter myAdapter = new MyAdapter(foodInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);

        return view;
    }
}

