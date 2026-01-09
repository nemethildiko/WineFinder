package com.example.winefinder.model;

import java.io.Serializable;

public class WineDto implements Serializable {

    private String winery;
    private String wine;
    private String image;
    private String location;
    private Rating rating;


    private boolean favorite;

    public String getWinery() { return winery; }
    public String getWine() { return wine; }
    public String getImage() { return image; }
    public String getLocation() { return location; }
    public Rating getRating() { return rating; }


    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public static class Rating {
        private double average;
        private String reviews;

        public double getAverage() { return average; }
        public String getReviews() { return reviews; }
    }
}
