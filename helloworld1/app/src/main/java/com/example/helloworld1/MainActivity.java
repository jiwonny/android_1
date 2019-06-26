package com.example.helloworld1;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    Fragment1 frag1;
    Fragment2 frag2;
    Fragment3 frag3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.bt1).setOnClickListener(this);
        findViewById(R.id.bt2).setOnClickListener(this);
        findViewById(R.id.bt3).setOnClickListener(this);
        frag1 = new Fragment1();
        frag2 = new Fragment2();
        frag3 = new Fragment3();
    }

                @Override
                public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.bt1:
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_frame, frag1)
                                .commit();
                        break;
                    case R.id.bt2:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, frag2)
                        .commit();
                break;
            case R.id.bt3:
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_frame, frag3)
                        .commit();
                break;
        }
    }
}
