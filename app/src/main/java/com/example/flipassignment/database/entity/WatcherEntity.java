package com.example.flipassignment.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "watcher_table")
public class WatcherEntity {
    @PrimaryKey(autoGenerate = true)
    private int w_id;
    private String id;
    private String name;

    public WatcherEntity(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getW_id() {
        return w_id;
    }

    public void setW_id(int w_id) {
        this.w_id = w_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
