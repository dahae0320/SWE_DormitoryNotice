package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdReceiver;
import com.google.firebase.iid.InstanceIdResult;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    Button btn_search;
    Button btn_refresh;
    Button mv_alarm;
    String title;
    String author;
    String date;
    String idx;
    String notice_num;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()) {
                            Log.w("FCM Log", "getInstanceId failed",task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();
                        Log.d("FCM Log","FCM 토큰: "+token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });


        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                noticeList.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    title = dataSnapshot.child(snapshot.getKey()).child("title").getValue().toString();
                    author = dataSnapshot.child(snapshot.getKey()).child("author").getValue().toString();
                    date = dataSnapshot.child(snapshot.getKey()).child("date").getValue().toString();
                    idx = dataSnapshot.child(snapshot.getKey()).child("notice_num").getValue().toString();
                    noticeList.add(new Notice(title, author, date,idx));
                }
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
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("idx",noticeList.get(position).index.toString());
                startActivity(intent);
            }
        });

        noticeListView.setAdapter(adapter);
        // 사용 불가능 에러 발생 -> 에러는 listview에 onclick을 작성했기 때문에 났던 에러이다. listview는 onitemclick을 이용하도록하자

        // 알람 페이지로 이동하는 버튼의 아이디 값을 통해 접근


        mv_alarm = findViewById(R.id.gotoalarm);
        mv_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            } // 알람 버튼을 클릭했을 때 알람 페이지 클래스로 이동함
        });

        // 검색 버튼으로 이동
        btn_search = findViewById(R.id.btnSearch);
        final EditText text_search = (EditText)findViewById(R.id.idSearchText);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = text_search.getText().toString();
                Intent intent2 = new Intent(MainActivity.this, SearchActivity.class);
                intent2.putExtra("text",text);
                startActivity(intent2);
            } // 검색 버튼을 클릭했을 때 검색 페이지 클래스로 이동함
        });

    }
    public String getIdx(int i){
        return Integer.toString(i);
    }
}
