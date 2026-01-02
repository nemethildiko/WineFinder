package com.example.winefinder.model;

public class WineDto {
    private String winery;
    private String wine;
    private String image;
    private String location;
    private Rating rating;

    public String getWinery() { return winery; }
    public String getWine() { return wine; }
    public String getImage() { return image; }
    public String getLocation() { return location; }
    public Rating getRating() { return rating; }

    public static class Rating {
        private double average;
        private String reviews;

        public double getAverage() { return average; }
        public String getReviews() { return reviews; }
    }
}
