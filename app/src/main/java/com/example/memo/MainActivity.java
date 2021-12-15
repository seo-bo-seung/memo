package com.example.memo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private int REQUEST_TEST = 200;
    private ImageButton btn_plus;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    PreferenceManager pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pref = new PreferenceManager();

        btn_plus = findViewById(R.id.btn_plus);
        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Add.class);
                startActivityForResult(intent, REQUEST_TEST);
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mainAdapter = new MainAdapter(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainAdapter);



        SharedPreferences prefb =getSharedPreferences("memo_contain", MODE_PRIVATE);
        Collection<?> col_val =  prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();

        while(it_val.hasNext() && it_key.hasNext()) {
            String key = (String) it_key.next();
            String value = (String) it_val.next();
            try {
                JSONObject jsonObject = new JSONObject(value);
                String title = (String) jsonObject.getString("title");
                String content = (String) jsonObject.getString("content");
                mainAdapter.addItem(new MemoItem(key, title, content));
            } catch (JSONException e) {
                Log.d("MainActivity","JSONObject : "+e);
            }

            mainAdapter.notifyDataSetChanged();

        }
        //pref.clear(MainActivity.this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEST) {
            if (resultCode == RESULT_OK) {

                Intent intent = getIntent();
                String get_date = data.getStringExtra("date");
                String get_title = data.getStringExtra("title");
                String get_content = data.getStringExtra("content");

                mainAdapter.addItem(new MemoItem(get_date,get_title,get_content));
                mainAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "작성 되었습니다", Toast.LENGTH_SHORT).show();

            } else {   // RESULT_CANCEL
                Toast.makeText(MainActivity.this, "저장 되지 않음", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

