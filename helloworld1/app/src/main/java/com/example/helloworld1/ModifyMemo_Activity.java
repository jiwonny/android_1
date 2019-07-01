package com.example.helloworld1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyMemo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MemoBook22.db", null, 1);
       // final TextView result = (TextView) findViewById(R.id.result);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        //final EditText etPrice = (EditText) findViewById(R.id.price);

        Intent i = getIntent();
        final String cur_date = i.getExtras().getString("date");
        etDate.setText(cur_date);


        //result.setText(dbHelper.getResultof(cur_date));
        // DB에 데이터 추가
        Button insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String item = etItem.getText().toString();
                //int price = Integer.parseInt(etPrice.getText().toString());

                dbHelper.insert(date, item);
                //result.setText(dbHelper.getResultof(cur_date));
            }
        });

//        // DB에 있는 데이터 수정
//        Button update = (Button) findViewById(R.id.update);
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String item = etItem.getText().toString();
//                //int price = Integer.parseInt(etPrice.getText().toString());
//
//                //dbHelper.update(item, price);
//                //result.setText(dbHelper.getResultof(cur_date));
//            }
//        });
//
//        // DB에 있는 데이터 삭제
//        Button delete = (Button) findViewById(R.id.delete);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String item = etItem.getText().toString();
//
//                dbHelper.delete(item);
//                //result.setText(dbHelper.getResultof(cur_date));
//            }
//        });
//
//        // DB에 있는 데이터 조회
//        Button select = (Button) findViewById(R.id.select);
//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //result.setText(dbHelper.getResultof(cur_date));
//            }
//        });

        Button exit = (Button) findViewById(R.id.onClick_exit1);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "나가기", Toast.LENGTH_SHORT).show();
                finish();
            }

        });


    }

}
