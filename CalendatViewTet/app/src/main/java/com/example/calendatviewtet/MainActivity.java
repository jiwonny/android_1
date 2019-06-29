package com.example.calendatviewtet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MemoBook.db", null, 1);

        //CalendarView 인스턴스 만들기
        // 날짜는 현재 날짜로 고정
        // 현재 시간 구하기
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");


        final String[] cur_date = {simpleDateFormat.format(date)};
        final TextView text_content = findViewById(R.id.text_content);
        text_content.setText(dbHelper.getResultof(cur_date[0]));

        CalendarView calendar = (CalendarView)findViewById(R.id.calendar);

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
        Button modify = (Button) findViewById(R.id.bt_modify);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ModifyMemo_Activity.class);
                i.putExtra("date", cur_date[0]);
                v.getContext().startActivity(i);
            }
        });


    }
}
