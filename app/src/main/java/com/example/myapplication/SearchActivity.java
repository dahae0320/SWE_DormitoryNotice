package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SearchActivity extends AppCompatActivity {

    Button btn_search;
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        btn_home = findViewById(R.id.gotohome);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(SearchActivity.this, MainActivity.class);
                startActivity(intent5);
            } // 알람 버튼을 클릭했을 때 알람 페이지 클래스로 이동함
        });

        btn_search = findViewById(R.id.btnSearch);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(intent6);
            } // 검색 버튼을 클릭했을 때 검색 페이지 클래스로 이동함
        });

    }
}