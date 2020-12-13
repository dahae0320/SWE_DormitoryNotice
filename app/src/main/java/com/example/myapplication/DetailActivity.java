package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// for DataSnapshot 사용
public class DetailActivity extends AppCompatActivity {

    Button btn_home;
    String noticeDescription;
    String noticeTitle;
    String noticeAuthor;
    String noticeDate;
    String noticeNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Intent intent = getIntent();
                String data = intent.getStringExtra("idx");
                noticeTitle = dataSnapshot.child(data).child("title").getValue().toString();
                noticeDescription = dataSnapshot.child(data).child("description").getValue().toString();
                noticeDate = dataSnapshot.child(data).child("date").getValue().toString();
                noticeNumber = dataSnapshot.child(data).child("notice_num").getValue().toString();
                noticeAuthor = dataSnapshot.child(data).child("author").getValue().toString();

                TextView noticeDetail = (TextView)findViewById(R.id.noticeDetail);
                TextView textDetail = (TextView)findViewById(R.id.textDetail);
                TextView idxDetail = (TextView)findViewById(R.id.noticeIdx);
                TextView authorDetail = (TextView)findViewById(R.id.nameText);
                TextView dateDetail = (TextView)findViewById(R.id.dateText);
                noticeDetail.setText(noticeTitle);
                textDetail.setText(noticeDescription);
                idxDetail.setText(noticeNumber);
                authorDetail.setText(noticeAuthor);
                dateDetail.setText(noticeDate);
                textDetail.setMovementMethod(new ScrollingMovementMethod());

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });




        btn_home = findViewById(R.id.backtohome);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent3);
            } // 홈 버튼을 클릭했을 때 메인 페이지 클래스로 이동함
        });
    }
    public String getIdx(int i){
        return Integer.toString(i);
    }
}

