package com.example.helloworld1;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView mRecyclerView;
        RecyclerView.LayoutManager mLayoutManager;

        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);


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