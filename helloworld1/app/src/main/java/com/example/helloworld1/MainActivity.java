package com.example.helloworld1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
public class MainActivity extends FragmentActivity implements View.OnClickListener {
    Fragment1 frag1;
    Fragment2 frag2;
    Fragment3 frag3;
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
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
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
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
