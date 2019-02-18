package com.example.flipassignment.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.flipassignment.database.entity.WatcherEntity;

import java.util.List;

@Dao
public interface WatcherDao {
    @Insert
    void insertWatcher(WatcherEntity watcherEntity);


    @Query("Select id from watcher_table")
    List<String> getAllWatcher();

    @Query("Select * from watcher_table")
    List<WatcherEntity> getAllWatcherData();
}
