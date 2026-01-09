package com.example.winefinder.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.winefinder.data.entity.FavoriteWineEntity;

import java.util.List;

@Dao
public interface FavoriteWineDao {

    @Insert
    void insert(FavoriteWineEntity wine);

    @Query("DELETE FROM favorites WHERE wineName = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM favorites")
    List<FavoriteWineEntity> getAll();

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE wineName = :name)")
    boolean isFavorite(String name);
}
