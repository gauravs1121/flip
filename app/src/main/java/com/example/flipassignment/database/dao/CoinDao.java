package com.example.flipassignment.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.flipassignment.database.entity.CoinEntity;

import java.util.List;

@Dao
public interface CoinDao {

    @Insert
    void insertCoin(List<CoinEntity> holderList);

    @Query("Select * from coin_table")
    List<CoinEntity> getAllCoins();

    @Query("DELETE FROM coin_table")
    void deleteAll();
}
