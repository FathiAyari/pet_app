package com.example.myapppeet;

public class Reminder {
    private int id;
    private String subject;
    private String date;

    public Reminder(int id, String subject, String date) {
        this.id = id;
        this.subject = subject;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getDate() {
        return date;
    }
}
