package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ListView noticeListView;  // 임시
    private NoticeListAdapter adapter;  // 임시
    private List<Notice> noticeList;  // 임시
    Button btn_search;
    Button btn_home;
    String title;
    String description;
    String author;
    String date;
    String idx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                noticeList.clear();
                Intent intent = getIntent();
                String data = intent.getStringExtra("text");

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    if(data.equals("")){
                        break;
                    }
                    title = dataSnapshot.child(snapshot.getKey()).child("title").getValue().toString();
                    description = dataSnapshot.child(snapshot.getKey()).child("description").getValue().toString();

                    if(data != null) {
                        if (title.contains(data)) {
                            author = dataSnapshot.child(snapshot.getKey()).child("author").getValue().toString();
                            date = dataSnapshot.child(snapshot.getKey()).child("date").getValue().toString();
                            idx = dataSnapshot.child(snapshot.getKey()).child("notice_num").getValue().toString();
                            noticeList.add(new Notice(title, author, date, idx));
                        }
                    }
                }
                data = null;
                Collections.reverse(noticeList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        adapter = new NoticeListAdapter(getApplicationContext(),noticeList);  // 임시
        noticeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
                intent.putExtra("idx",noticeList.get(position).index.toString());
                startActivity(intent);
            }
        });
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


        btn_search = findViewById(R.id.btnSearch);
        final EditText text_search = (EditText)findViewById(R.id.idSearchText);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = text_search.getText().toString();
                Intent intent2 = new Intent(SearchActivity.this, SearchActivity.class);
                intent2.putExtra("text",text);
                startActivity(intent2);
            } // 검색 버튼을 클릭했을 때 검색 페이지 클래스로 이동함
        });
    }

}

