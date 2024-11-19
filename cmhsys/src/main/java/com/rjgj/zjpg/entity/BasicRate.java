package com.rjgj.zjpg.entity;

public class BasicRate {

    private String city;
    private int year;
    private double cost;

    public BasicRate(String stdName, String city, int year, double cost) {

        this.city = city;
        this.year = year;
        this.cost = cost;
    }
    public BasicRate() {}


    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }


    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public double getCost() {
        return cost;
    }
}
