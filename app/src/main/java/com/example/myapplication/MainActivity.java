package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;
    Button mv_alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();

        noticeList.add(new Notice(" 2020.2학기 중 생활관실 수시점검 실시 안내","주권희","2020-10-21"));
        noticeList.add(new Notice(" 2020학년도 2학기 벌점 사항 공지(2차)","주권희","2020-10-17"));
        noticeList.add(new Notice(" 학생생활관 방역 안내 ","주권희","2020-10-12"));
        noticeList.add(new Notice(" 2020학년도 2학기 벌점 사항 공지(1차)","주권희","2020-10-8"));
        noticeList.add(new Notice(" 2019년 냉방종료 및 난방공급 안내)","임병규","2020-09-28"));
        noticeList.add(new Notice(" (중대본)추석 연휴 생활방역 수칙 ","김경영","2020-09-21"));
        noticeList.add(new Notice(" 외박신고서 작성 안내 ","학생생활관(가좌) 관리자","2020-09-18"));


        adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(adapter);

        /*ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/




        // 알람 페이지로 이동하는 버튼의 아이디 값을 통해 접근
        mv_alarm = findViewById(R.id.gotoalarm);

        mv_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AlarmActivity.class);
                startActivity(intent);
            } // 알람 버튼을 클릭했을 때 알람 페이지 클래스로 이동함
        });




    }
}

//private class Content extends AsyncTask<Void, Void, Void> {
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//        progressDialog = new ProgressDialog(MainActivity.this);
//        progressDialog.show();
//    }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        try {
//            //Connect to the website
//            Document document = Jsoup.connect(url).get();
//
//            //Get the logo source of the website
//            Element img = document.select("img").first();
//            // Locate the src attribute
//            String imgSrc = img.absUrl("src");
//            // Download image from URL
//            InputStream input = new java.net.URL(imgSrc).openStream();
//            // Decode Bitmap
//            bitmap = BitmapFactory.decodeStream(input);
//
//            //Get the title of the website
//            title = document.title();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Void aVoid) {
//        super.onPostExecute(aVoid);
//
//        imageView.setImageBitmap(bitmap);
//        textView.setText(title);
//        progressDialog.dismiss();
//    }
}