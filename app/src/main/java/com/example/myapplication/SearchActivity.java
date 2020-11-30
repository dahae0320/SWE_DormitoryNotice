package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ListView noticeListView;  // 임시
    private NoticeListAdapter adapter;  // 임시
    private List<Notice> noticeList;  // 임시
    Button btn_search;
    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("1000");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title;
                String author;
                String date;
                noticeList.add(new Notice("d","d","d","d"));
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        adapter = new NoticeListAdapter(getApplicationContext(),noticeList);  // 임시
        noticeListView.setAdapter(adapter);

          // 임시
        //noticeListView = (ListView) findViewById(R.id.noticeListView);  // 임시
        //noticeList = new ArrayList<Notice>();  // 임시

        //noticeList.add(new Notice()); // 임시
        //noticeList.add(new Notice("d","d","d")); // 임시


        //adapter = new NoticeListAdapter(getApplicationContext(),noticeList);  // 임시
        //noticeListView.setAdapter(adapter);  // 임시

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

