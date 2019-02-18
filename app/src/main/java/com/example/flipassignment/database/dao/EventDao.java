package com.example.flipassignment.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.flipassignment.database.entity.EventEntity;

import java.util.List;

@Dao
public interface EventDao {
    @Insert
    void insertEvent(List<EventEntity> holderList);

    @Query("Select * from event_table")
    List<EventEntity> getAllEvents();

    @Query("DELETE FROM event_table")
    void deleteAllEvent();
}
