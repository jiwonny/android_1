package com.example.helloworld1;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

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
    RecyclerView mRecyclerView;
    EditText editSearch;
    ArrayList<FoodInfo> dInfoArrayList = new ArrayList<>();
    ArrayList<FoodInfo> foodInfoArrayList = new ArrayList<>();
    MyAdapter myAdapter;
    String address;


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

        RecyclerView.LayoutManager mLayoutManager;



        View view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        editSearch = view.findViewById(R.id.editSearch);
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);



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
            foodInfoArrayList.clear();
            dInfoArrayList.clear();
            for(int i=0; i < jarray.length(); i++){
                JSONObject jObject = jarray.getJSONObject(i);  // JSONObject 추출
                String phone = jObject.getString("phone");
                String name = jObject.getString("name");
                Long id = jObject.getLong("id");
                Long image_id = jObject.getLong("image_id");

                foodInfoArrayList.add(new FoodInfo(id, image_id, name, phone));
                dInfoArrayList.add(new FoodInfo(id, image_id, name, phone));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }




        myAdapter = new MyAdapter(foodInfoArrayList);
        mRecyclerView.setAdapter(myAdapter);

        editSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        return view;
    }
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        foodInfoArrayList.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            foodInfoArrayList.addAll(dInfoArrayList);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < dInfoArrayList.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (dInfoArrayList.get(i).name.toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    foodInfoArrayList.add(dInfoArrayList.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        myAdapter.notifyDataSetChanged();
    }
}