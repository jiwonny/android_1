package com.example.helloworld1;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteMemo_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "MemoBook30.db", null, 1);
        // final TextView result = (TextView) findViewById(R.id.result);

        Intent i = getIntent();
        final String memo_id = i.getExtras().getString("date").split("&&")[0];

        dbHelper.delete(memo_id);
        //result.setText(dbHelper.getResultof(cur_date));
       finish();


    }

}
