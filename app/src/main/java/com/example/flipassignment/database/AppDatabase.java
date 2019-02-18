package com.example.flipassignment.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.flipassignment.database.dao.CoinDao;
import com.example.flipassignment.database.dao.EventDao;
import com.example.flipassignment.database.dao.WatcherDao;
import com.example.flipassignment.database.entity.CoinEntity;
import com.example.flipassignment.database.entity.EventEntity;
import com.example.flipassignment.database.entity.WatcherEntity;

@Database(entities ={CoinEntity.class, WatcherEntity.class, EventEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CoinDao coinDao();
    public abstract WatcherDao watcherDao();
    public abstract EventDao eventDao();
    private static volatile AppDatabase INSTANCE; //singleton

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_db")
                            .fallbackToDestructiveMigration() // it will delete database with all tables in case of no migration
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
