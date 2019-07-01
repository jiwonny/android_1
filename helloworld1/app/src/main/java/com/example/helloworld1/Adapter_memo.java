package com.example.helloworld1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_memo extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        TextView tvMemo;
        Button btEdit;
        Button btDelete;
        LinearLayout lll;

//        LinearLayout parentLayout;

        public ContentViewHolder(View view){
            super(view);

            tvMemo = view.findViewById(R.id.tv_memo);
            btEdit = view.findViewById(R.id.bt_edit);
            btDelete = view.findViewById(R.id.bt_delete);
            lll = view.findViewById(R.id.lll);

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

        final ContentViewHolder Holder = (ContentViewHolder) holder;
        Fragment3 fragment3 = new Fragment3();

        final DBHelper dbHelper = new DBHelper(Holder.tvMemo.getContext(), "MemoBook30.db", null, 1);
        Holder.tvMemo.setText(MemoInfoArrayList.get(position).content);

        Holder.btEdit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Toast.makeText(view.getContext(), "수정하기", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(view.getContext(), ModifyMemo_Activity.class);
                i.putExtra("date", MemoInfoArrayList.get(position).id+"&&"+MemoInfoArrayList.get(position).date + "&&"+MemoInfoArrayList.get(position).content);

                ((Activity) view.getContext()).startActivityForResult(i, 1);
                //String modified_memo = dbHelper.select(Integer.toString(MemoInfoArrayList.get(position).id));
                //Holder.tvMemo.setText(modified_memo);

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

                                ((Activity) view.getContext()).startActivityForResult(i, 0);
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

    }

    @Override
    public int getItemCount() {
        return MemoInfoArrayList.size();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        //DBHelper dbHelper = new DBHelper(view.getContext(), "MemoBook30.db", null, 1);
        switch (requestCode){
            case 0:
                notifyDataSetChanged();
                break;
        }

    }
}
