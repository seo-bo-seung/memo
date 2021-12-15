package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReWriteActivity extends AppCompatActivity {

    PreferenceManager pref;
    ImageButton btn_back;
    ImageButton btn_check;
    EditText title;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_write);

        pref = new PreferenceManager();
        btn_back = findViewById(R.id.btn_back);
        btn_check = findViewById(R.id.btn_check);
        //title = findViewById(R.id.edit_memo_title);
        //content = findViewById(R.id.edit_memo_content);

        Intent get_intent = getIntent();
        String get_key = get_intent.getStringExtra("key");
        String get_title = get_intent.getStringExtra("title");
        String get_content = get_intent.getStringExtra("content");
        Log.d("ReWrite",get_title+"/"+get_content);

        title.setText(get_title);
        content.setText(get_content);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReWriteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit_title = title.getText().toString();
                String edit_content = content.getText().toString();
                // String 값을 JSONObject로 변환하여 사용할 수 있도록 메모의 제목과 타이틀을 JSON 형식로 저장
                String save_form = "{\"title\":\""+edit_title+"\",\"content\":\""+edit_content+"\"}";


                Log.d("WriteActivity","제목 : "+edit_title+", 내용 : "+edit_content+", 현재시간 : "+get_key);
                //PreferenceManager 클래스에서 저장에 관한 메소드를 관리
                pref.setString(getApplication(),get_key,save_form);


                // Intent로 값을 MainActivity에 전달
                Intent intent = new Intent();
                intent.putExtra("date",get_key);
                intent.putExtra("title",edit_title);
                intent.putExtra("content",edit_content);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}