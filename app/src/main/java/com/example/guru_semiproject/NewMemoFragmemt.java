package com.example.guru_semiproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_semiproject.model.MyMemo;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class NewMemoFragmemt extends Fragment {

    private List<MyMemo> memos = new ArrayList<>();

    public NewMemoFragmemt() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.new_memo_fragmemt, container, false);

        Button btnWrite = view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MemoWriteActivity.class);
                startActivity(intent);
            }
        });

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        memos.add(new MyMemo("메모입니다. 첫번째 메모입니다.", "20190704", R.drawable.twitter));
        memos.add(new MyMemo("메모입니다. 두번째 메모입니다.", "20190705", R.drawable.twitter));
        memos.add(new MyMemo("메모입니다. 세번째 메모입니다.", "20190706", R.drawable.twitter));

        MyAdapter myAdapter = new MyAdapter(memos);
        recyclerView.setAdapter(myAdapter);

//        Button btnUpdate = view.findViewById(R.id.btnUpdate);
//        Button btnRemove = view.findViewById(R.id.btnRemove);
//        Button btnDetail = view.findViewById(R.id.btnDetail);
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), MemoupdateActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.CustomViewHolder> {
        private List<MyMemo> memos; //원본데이터

        // 생성자
        public MyAdapter(List<MyMemo> items) {
            this.memos = items;
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder {
            protected ImageView memoImg;
            protected TextView memoDate;
            protected TextView memoContent;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.memoImg = itemView.findViewById(R.id.memoImg);
                this.memoContent = itemView.findViewById(R.id.memoContent);
                this.memoDate = itemView.findViewById(R.id.memoDate);

            }
        }

        @NonNull
        @Override
        public MyAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.memo_list, parent, false);

            CustomViewHolder viewHolder = new CustomViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyAdapter.CustomViewHolder holder, final int position) {
            MyMemo memo = memos.get(position);

            // Holder의 각요소에 데이터 설정
            holder.memoContent.setText(memo.getMemoContent());
            holder.memoDate.setText(memo.getMemoDate());
            holder.memoImg.setImageResource(memo.getImageId());

        }

        @Override
        public int getItemCount() {
            return memos.size();
        }
    }
}