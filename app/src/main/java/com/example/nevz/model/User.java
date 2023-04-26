package com.example.nevz.model;

public class User {
    private int id;
    private String name;
    private String surName;

    public User() {
    }

    public User(int id, String name, String surdName) {
        this.id = id;
        this.name = name;
        this.surName = surdName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }
}