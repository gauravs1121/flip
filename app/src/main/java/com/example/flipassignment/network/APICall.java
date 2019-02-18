package com.example.flipassignment.network;

import com.example.flipassignment.database.entity.CoinEntity;
import com.example.flipassignment.holder.CoinDetailHolder;
import com.example.flipassignment.holder.CurrencyData;
import com.example.flipassignment.holder.EventHolder;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APICall {
    @GET("coins/list")
    Call<List<CoinEntity>> getCoinList();

    @GET("simple/price")
    Call<Map<String, CurrencyData>> getWatcherData(@Query("ids") String ids,
                                                   @Query("vs_currencies") String currencies);

    @GET("events")
    Call<EventHolder> getEvent(@Query("from_date") String startDate,
                               @Query("to_date") String endDate);

    @GET("coins/{id}")
    Call<CoinDetailHolder> getCoinDetail(@Path("id")String id);

}
