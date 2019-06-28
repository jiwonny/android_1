package com.example.helloworld1;

import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedHashSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public Fragment1(){

    }
    public String getContactList() {


        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.Contacts.PHOTO_ID,
                ContactsContract.Contacts._ID
        };
        String address;
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, projection, null,
                selectionArgs, sortOrder);
        address = "[";
        if (cursor.moveToFirst()) {
            do {
                long photo_id = cursor.getLong(2);
                long person_id = cursor.getLong(3);
                String name = cursor.getString(1);
                String phone = cursor.getString(0);
                String x = "{" + "'name':" + "'" + name + "'" + ","+"'phone':" + "'" + phone + "'," +"'image_id':" +"'" + String.valueOf(photo_id) +"',"+ "'id':"+"'" + String.valueOf(person_id) +"'}, ";
                address += x;
            } while (cursor.moveToNext());
        }
        address = address.substring(0,address.length()-2);
        address += "]";
        return address;
    }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_CONTACTS)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_CONTACTS},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }



        ArrayList<FoodInfo> foodInfoArrayList = new ArrayList<>();
        String address = getContactList();
        /*
        String address =
                "[{'name':'김현석', 'phone':'010-0000-0000'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'이현석', 'phone':'010-1111-1111'},"+
                        "{'name':'양현석', 'phone':'010-2222-2222'}]";
*/
        try {
            JSONArray jarray = new JSONArray(address);   // JSONArray 생성
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String phone = jObject.getString("phone");
                String name = jObject.getString("name");
                Long id = jObject.getLong("id");
                Long image_id = jObject.getLong("image_id");
                foodInfoArrayList.add(new FoodInfo(id, image_id, name, phone));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        MyAdapter myAdapter = new MyAdapter(foodInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);

        return view;
    }
}

