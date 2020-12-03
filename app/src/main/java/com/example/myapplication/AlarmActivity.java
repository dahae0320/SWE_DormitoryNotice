package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class AlarmActivity extends AppCompatActivity {

    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //firebase 알림 연결 설정
        FirebaseInstanceId.getInstance().getInstanceId() //클래스의 인스턴스 아이디를 받아 addOnCompleteListener(task) 수행
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if(!task.isSuccessful()) { //task를 수행하는데 오류가 생겼습니다.
                            Log.w("FCM Log", "getInstanceId failed",task.getException());
                            return;
                        }
                        String token = task.getResult().getToken();  // token 변수에 해당 토큰을 받아옴
                        Log.d("FCM Log","FCM 토큰: "+token);
                        Toast.makeText(AlarmActivity.this, token, Toast.LENGTH_SHORT).show(); //Toast 클래스의 LENGTH_SHORT를 통해 간단한 메세지를 짧은시간동안 보여줌
                    }
                });

        btn_home = findViewById(R.id.backtohome);

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(AlarmActivity.this, MainActivity.class);
                startActivity(intent4);
            } // 홈 버튼을 클릭했을 때 메인 페이지 클래스로 이동함
        });
    }
}