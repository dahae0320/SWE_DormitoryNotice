package com.example.myapplication;

public class Notice {
    String title; // notice
    String author; // name
    String date;
    String description; // text
    String index;

    public Notice(String title, String author, String date) {
        this.title = title;
        this.author = author;
        this.date = date;

    }
    public Notice(String title, String author, String date,String index) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.index=index;
    }
    public Notice(String title, String author, String date,String index,String description) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;
        this.index=index;
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

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() { return description;}

    public void setDescription(String description) {this.description=description;}

    public String getIndex() {return index;}

    public void setIndex(String index) {this.index= index;}
}
