package com.example.winefinder.model;

public class Wine {
    private final String name;
    private final String country;
    private final int year;

    public Wine(String name, String country, int year) {
        this.name = name;
        this.country = country;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getYear() {
        return year;
    }
}
