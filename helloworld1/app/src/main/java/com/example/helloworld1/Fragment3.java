package com.example.helloworld1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    Adapter_memo myAdapter;
    ArrayList<MemoInfo> dInfoArrayList = new ArrayList<>();
    ArrayList<MemoInfo> MemoInfoArrayList = new ArrayList<>();
    public DBHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = view.findViewById(R.id.recycler_view_memo);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        final DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext(), "MemoBook30.db", null, 1);

        

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/d");


        final String[] cur_date = {simpleDateFormat.format(date)};
        Log.i("asd", cur_date[0]);
        dInfoArrayList = dbHelper.getResultof(cur_date[0]);
        for (int i=0; i<dInfoArrayList.size(); i++){
            MemoInfoArrayList.add(new MemoInfo(dInfoArrayList.get(i).date,dInfoArrayList.get(i).content));
        }

        myAdapter = new Adapter_memo(MemoInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);


        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                // TODO Auto-generated method stub
                cur_date[0] = year+"/"+(month+1)+"/" +dayOfMonth;
                Log.i("asd", cur_date[0]);
                dInfoArrayList = dbHelper.getResultof(cur_date[0]);
                MemoInfoArrayList.clear();
                for (int i=0; i<dInfoArrayList.size(); i++){
                    MemoInfoArrayList.add(new MemoInfo(dInfoArrayList.get(i).date,dInfoArrayList.get(i).content));
                }
                myAdapter.notifyDataSetChanged();


            }



            });
//        Button modify = (Button) view.findViewById(R.id.bt_modify);
//        modify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

            FloatingActionButton fab = view.findViewById(R.id.fab);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ModifyMemo_Activity.class);

                //i.putExtra("mia", MemoInfoArrayList);
                    //i.putExtra("db", dbHelper);
                    i.putExtra("date", cur_date[0]);
                    startActivityForResult(i,0);
                //v.getContext().startActivity(i);



                }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext(), "MemoBook30.db", null, 1);
        switch (requestCode){
            case 0:
                String mdate = data.getStringExtra("date");
                ArrayList<String> mitem = data.getStringArrayListExtra("item");
                for(int e=0; e<mitem.size(); e++){
                    Log.i("asd",mdate);
                    Log.i("asd",mitem.get(e));
                    dbHelper.insert(mdate, mitem.get(e));
                }

                dInfoArrayList = dbHelper.getResultof(mdate);
                MemoInfoArrayList.clear();
                for (int i=0; i<dInfoArrayList.size(); i++){
                    MemoInfoArrayList.add(new MemoInfo(dInfoArrayList.get(i).date,dInfoArrayList.get(i).content));
                }
                myAdapter.notifyDataSetChanged();
                break;
        }

    }
}
