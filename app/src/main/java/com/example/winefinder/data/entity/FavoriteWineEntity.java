package com.example.winefinder.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoriteWineEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String wineName;
    public String winery;
    public String image;
}
