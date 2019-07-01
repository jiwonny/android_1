package com.example.helloworld1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ModifyMemo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editmemo);

        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MemoBook30.db", null, 1);
       // final TextView result = (TextView) findViewById(R.id.result);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        //final EditText etPrice = (EditText) findViewById(R.id.price);

        Intent i = getIntent();
        final String memo_id = i.getExtras().getString("date").split("&&")[0];
        final String cur_date = i.getExtras().getString("date").split("&&")[1];
        final String content = i.getExtras().getString("date").split("&&")[2];
        etDate.setText(cur_date);
        etItem.setText(content, TextView.BufferType.EDITABLE);


        // DB에 있는 데이터 수정
        Button update = (Button) findViewById(R.id.edit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String item = etItem.getText().toString();
                //int price = Integer.parseInt(etPrice.getText().toString());
                Toast.makeText(v.getContext(), "일정이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                dbHelper.update(item, memo_id);
                finish();
                //result.setText(dbHelper.getResultof(cur_date));
            }
        });
//

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
