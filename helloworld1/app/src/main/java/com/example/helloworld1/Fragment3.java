package com.example.helloworld1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment3, container, false);
        final DBHelper dbHelper = new DBHelper(getActivity().getApplicationContext(), "MemoBook.db", null, 1);

        //CalendarView 인스턴스 만들기
        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");


        final String[] cur_date = {simpleDateFormat.format(date)};
        final TextView text_content = view.findViewById(R.id.text_content);
        text_content.setText(dbHelper.getResultof(cur_date[0]));

        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);

        //리스너 등록
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                // TODO Auto-generated method stub


                cur_date[0] = ""+year+"/"+(month+1)+"/" +dayOfMonth;
                text_content.setText(dbHelper.getResultof(cur_date[0]));

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
                i.putExtra("date", cur_date[0]);
                v.getContext().startActivity(i);
            }
        });
        return view;
    }
}
