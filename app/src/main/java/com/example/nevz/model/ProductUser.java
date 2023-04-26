package com.example.nevz.model;

public class ProductUser {
    private int day;
    private String product;
    private int machine;
    private int count;

    public ProductUser() {
    }

    public ProductUser(int day, String product, int machine, int count) {
        this.day = day;
        this.product = product;
        this.machine = machine;
        this.count = count;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getMachine() {
        return machine;
    }

    public void setMachine(int machine) {
        this.machine = machine;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
