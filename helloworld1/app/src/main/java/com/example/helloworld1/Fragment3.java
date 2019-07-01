package com.example.helloworld1;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    View view;
    RecyclerView mRecyclerView;
    Adapter_memo myAdapter;
    ArrayList<MemoInfo> dInfoArrayList = new ArrayList<>();
    ArrayList<MemoInfo> MemoInfoArrayList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        RecyclerView.LayoutManager mLayoutManager;
        mRecyclerView = view.findViewById(R.id.recycler_view_memo);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext(), "MemoBook22.db", null, 1);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");


        final String[] cur_date = {simpleDateFormat.format(date)};

       // final TextView text_content = view.findViewById(R.id.text_content);
        final TextView date_text = view.findViewById(R.id.date_text);

//        text_content.setText(dbHelper.getResultof(cur_date[0]));
        String date_title = cur_date[0].split("/")[1]+" 월 "+cur_date[0].split("/")[2]+" 일" + "  일정";
        date_text.setText(date_title);
       // CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);

        dInfoArrayList = dbHelper.getResultof(cur_date[0]);
        for (int i=0; i<dInfoArrayList.size(); i++){
            MemoInfoArrayList.add(new MemoInfo(dInfoArrayList.get(i).id, dInfoArrayList.get(i).date,dInfoArrayList.get(i).content));
        }

        myAdapter = new Adapter_memo(MemoInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);


        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                // TODO Auto-generated method stub
                cur_date[0] = ""+year+"/"+(month+1)+"/" +dayOfMonth;

                Log.i("선택",cur_date[0]);

                String date_title = (month+1)+ " 월 " + dayOfMonth + " 일 일정";
                date_text.setText(date_title);

                dInfoArrayList = dbHelper.getResultof(cur_date[0]);
                MemoInfoArrayList.clear();
                for (int i=0; i<dInfoArrayList.size(); i++){
                    MemoInfoArrayList.add(new MemoInfo(dInfoArrayList.get(i).id, dInfoArrayList.get(i).date,dInfoArrayList.get(i).content));
                }
                myAdapter.notifyDataSetChanged();


            }



            });

//        Button modify = (Button) view.findViewById(R.id.bt_edit);
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
                Intent i = new Intent(v.getContext(), AddMemo_Activity.class);
                i.putExtra("date", cur_date[0]);
                v.getContext().startActivity(i);
            }
        });
        return view;
    }
}
