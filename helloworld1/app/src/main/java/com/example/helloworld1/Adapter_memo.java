package com.example.helloworld1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_memo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemo;
        Button btEdit;
        Button btDelete;
//        LinearLayout parentLayout;

        public ContentViewHolder(View view){
            super(view);
            tvMemo = view.findViewById(R.id.tv_memo);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);

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

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ContentViewHolder Holder = (ContentViewHolder) holder;

        Holder.tvMemo.setText(MemoInfoArrayList.get(position).content);

        Holder.btEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(view.getContext(), "수정하기", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(), ModifyMemo_Activity.class);
                i.putExtra("date", MemoInfoArrayList.get(position).id+"&&"+MemoInfoArrayList.get(position).date + "&&"+MemoInfoArrayList.get(position).content);

                view.getContext().startActivity(i);

            }
        });
        Holder.btDelete.setOnClickListener(new View.OnClickListener(){
            public void onClick(final View view){

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setCancelable(true);
                builder.setTitle("삭제");
                builder.setMessage("해당 일정을 삭제하시겠습니까?");
                builder.setPositiveButton("삭제",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(view.getContext(), DeleteMemo_Activity.class);
                                i.putExtra("date", MemoInfoArrayList.get(position).id+"&&"+MemoInfoArrayList.get(position).date + "&&"+MemoInfoArrayList.get(position).content);

                                view.getContext().startActivity(i);
                                Toast.makeText(view.getContext(), "일정이 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                            }
                        });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

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
