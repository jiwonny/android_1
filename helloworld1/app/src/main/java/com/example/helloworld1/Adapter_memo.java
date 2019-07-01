package com.example.helloworld1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld1.MemoInfo;
import com.example.helloworld1.R;

import java.util.ArrayList;

public class Adapter_memo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemo;
//        LinearLayout parentLayout;

        public ContentViewHolder(View view){
            super(view);
            tvMemo = view.findViewById(R.id.tv_memo);
//            parentLayout = view.findViewById(R.id.linearLayout);
        }
    }

    private ArrayList<MemoInfo> MemoInfoArrayList;
    Adapter_memo(ArrayList<MemoInfo> memoInfoAL){
        this.MemoInfoArrayList = memoInfoAL;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row_memo, parent, false);

        return new ContentViewHolder(v);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ContentViewHolder Holder = (ContentViewHolder) holder;

        Holder.tvMemo.setText(MemoInfoArrayList.get(position).content);
//        final String Memo = MemoInfoArrayList.get(position).content;
/*
        myViewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(view.getContext(), "연락처 보기", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(), subactivity_contact.class);
                i.putExtra("name", );

                view.getContext().startActivity(i);
            }
        });
        */
    }
    @Override
    public int getItemCount() {
        return MemoInfoArrayList.size();
    }
}
