package com.example.myapppeet;

import java.util.Date;

public class Event {
    private int id;
    private String label;

    // Constructor
    public Event(int id, String label) {
        this.id = id;
        this.label = label;
    }

    // Default Constructor
    public Event() {
    }

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for label
    public String getLabel() {
        return label;
    }

    // Setter for label
    public void setLabel(String label) {
        this.label = label;
    }



}
