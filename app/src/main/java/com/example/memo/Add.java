package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Add extends AppCompatActivity {

    private ImageButton btn_back;
    private ImageButton btn_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        btn_back = findViewById(R.id.btn_back);
        btn_check = findViewById(R.id.btn_check);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}