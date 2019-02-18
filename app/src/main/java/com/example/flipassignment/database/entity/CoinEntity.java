package com.example.flipassignment.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "coin_table")
public class CoinEntity {
    @PrimaryKey(autoGenerate = true)
    private int p_id;
    private String id;
    private String symbol;
    private String name;

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public CoinEntity(String id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }


}
