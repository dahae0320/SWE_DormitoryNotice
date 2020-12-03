package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

// for DataSnapshot 사용
public class DetailActivity extends AppCompatActivity {

    Button btn_home;
    String description;
    String title;
    String author;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(int i = 1080; i>1070; i--) {
                    title = dataSnapshot.child(getIdx(i)).child("title").getValue().toString();
                    author = dataSnapshot.child(getIdx(i)).child("author").getValue().toString();
                    date = dataSnapshot.child(getIdx(i)).child("date").getValue().toString();
                    description = dataSnapshot.child(getIdx(i)).child("description").getValue().toString();


                }
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

