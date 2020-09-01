package com.example.my_dictinary;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WordModel {
    private int id;
    private String ukrainian, english;
    private Date date;


    public WordModel(int id, String english, String ukrainian, Date date) {
        this.id = id;
        this.ukrainian = ukrainian;
        this.english = english;
        this.date = date;
    }

    public WordModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUkrainian() {
        return ukrainian;
    }

    public void setUkrainian(String ukrainian) {
        this.ukrainian = ukrainian;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public Date getDate() { return date; }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String currentDate = dateFormat.format(date);
        return "WordModel{" +
                "id=" + id +
                ", ukrainian='" + ukrainian + '\'' +
                ", english='" + english + '\'' +
                ", date=" + currentDate +
                '}';
    }
}
