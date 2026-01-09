package com.example.winefinder.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.winefinder.data.dao.FavoriteWineDao;
import com.example.winefinder.data.entity.FavoriteWineEntity;

@Database(entities = {FavoriteWineEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
// ez az adatbázis osztály
    private static AppDatabase INSTANCE;

    public abstract FavoriteWineDao favoriteWineDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "wine_database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
