package com.example.myapppeet;

public class Animal {
    private int id;
    private String name;
    private String type;
    private int age;  // Age is now an integer

    // Constructor, getters, and setters
    public Animal(int id, String name, String type, int age) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }
}
