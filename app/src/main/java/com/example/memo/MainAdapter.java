package com.example.memo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Activity mcontext;

    // Memo 객체를 담을 MemoItem / 작성일/제목/내용 이 담긴다
    private ArrayList<MemoItem> items = new ArrayList<>();


    public MainAdapter(Activity context) {
        mcontext = context;
    }

    // 리사이클러뷰에 데이터 추가 메소드
    public void addItem(MemoItem item) {
        items.add(item);
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        // 미리 만들어 놓은 item_rv_memo.xml 기입
        View view = inflater.inflate(R.layout.item_rv_memo, parent, false) ;
        MainAdapter.ViewHolder vh = new MainAdapter.ViewHolder(view) ;

        return vh;
    }

    @Override
    public int getItemCount() {
        return items.size() ;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        MemoItem item = items.get(position);
        // 메모 아이템 xml상에 메모 데이터가 적용되도록 세팅
        holder.setItem(item);

        // 메모 아이템 안에 있는 보기 버튼을 클릭하여 상세보기(ViewActivity)로 이동
        holder.view_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, ViewActivity.class);
                intent.putExtra("key",holder.date.getText().toString());
                mcontext.startActivity(intent);
            }
        });

        /*
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext, CacelPopup.class);
                intent.putExtra("key",holder.date.getText().toString());
                intent.putExtra("postion",position);
                mcontext.startActivityForResult(intent, 201);
                Log.d("Adapter", "삭제 버튼 누름");
            }
        });*/
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView title;
        Button view_btn;
        Button delete_btn;

        ViewHolder(View itemView){
            super(itemView);

            //뷰홀더에 필요한 아이템데이터 findview
            date = itemView.findViewById(R.id.date_contain);//아이템에 들어갈 텍스트
            title = itemView.findViewById(R.id.title_contain);//아이템에 들어갈 텍스트
            view_btn = itemView.findViewById(R.id.view_btn);
            delete_btn = itemView.findViewById(R.id.delete_btn);
        }
        //아이템뷰에 binding할 데이터
        public void setItem(MemoItem item) {
            date.setText(item.getDate());
            title.setText(item.getTitle());
        }
    }


}
