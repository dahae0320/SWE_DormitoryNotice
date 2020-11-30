package com.example.myapplication;

public class Notice {
    String title; // notice
    String author; // name
    String date;
    String description; // text

    public Notice(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;

    }
    public Notice(String title, String author, String date,String description) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setName(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDescription() { return description;}

}
