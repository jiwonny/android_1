package com.example.helloworld1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

    public class MainActivity extends FragmentActivity implements View.OnClickListener {
        public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
        private static final int MY_PERMISSION_CAMERA = 1111;
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
                case MY_PERMISSION_CAMERA:
                    for (int i = 0; i < grantResults.length; i++) {
                        // grantResults[] : 허용된 권한은 0, 거부한 권한은 -1
                        if (grantResults[i] < 0) {
                            Toast.makeText(this, "해당 권한을 활성화 하셔야 합니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    // 허용했다면 이 부분에서..
                    break;

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }

        Fragment1 frag1;
        Fragment2 frag2;
        Fragment3 frag3;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            //findViewById(R.id.bt1).setOnClickListener(this);
            findViewById(R.id.bt2).setOnClickListener(this);
            findViewById(R.id.bt3).setOnClickListener(this);
           // frag1 = new Fragment1();
            frag2 = new Fragment2();
            frag3 = new Fragment3();
            // Here, thisActivity is the current activity
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.READ_CONTACTS)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                // Should we show an explanation?
//                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
//                        Manifest.permission.READ_CONTACTS)) {
//
//                    // Show an expanation to the user *asynchronously* -- don't block
//                    // this thread waiting for the user's response! After the user
//                    // sees the explanation, try again to request the permission.
//
//                } else {
//
//                    // No explanation needed, we can request the permission.
//
//                    ActivityCompat.requestPermissions(this,
//                            new String[]{Manifest.permission.READ_CONTACTS},
//                            MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//
//                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
//                    // app-defined int constant. The callback method gets the
//                    // result of the request.
//                }
//            }

            checkPermission();


        }

        private void checkPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // 다시 보지 않기 버튼을 만드려면 이 부분에 바로 요청을 하도록 하면 됨 (아래 else{..} 부분 제거)
                // ActivityCompat.requestPermissions((Activity)mContext, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);

                // 처음 호출시엔 if()안의 부분은 false로 리턴 됨 -> else{..}의 요청으로 넘어감
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    new AlertDialog.Builder(this)
                            .setTitle("알림")
                            .setMessage("저장소 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                            .setNeutralButton("설정", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getPackageName()));
                                    startActivity(intent);
                                }
                            })
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                  finish();
                                }
                            })
                            .setCancelable(false)
                            .create()
                            .show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_CAMERA);
                }
            }
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.bt1:
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.main_frame, frag1)
                            .commit();
                    break;
                case R.id.bt2:
                    getFragmentManager()
                            .beginTransaction()
                            .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                            .replace(R.id.main_frame, frag2)
                            .commit();
                    break;
            case R.id.bt3:
                getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .replace(R.id.main_frame, frag3)
                        .commit();
                break;
        }
    }
}
