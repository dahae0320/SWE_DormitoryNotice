package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter adapter;
    private List<Notice> noticeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));
        noticeList.add(new Notice("공지사항입니다. ","강민규","2020-11-02"));


        adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(adapter);
    }
}