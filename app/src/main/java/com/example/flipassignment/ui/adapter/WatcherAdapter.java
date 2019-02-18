package com.example.flipassignment.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flipassignment.R;
import com.example.flipassignment.holder.CurrencyData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WatcherAdapter extends RecyclerView.Adapter<WatcherAdapter.WatcherHolder> {
    private Context context;
    private Map<String, CurrencyData> holderMap;
    private HashMap<String, String> idValue;
    private ArrayList<String> ids;

    public WatcherAdapter(Context context, Map<String, CurrencyData> holderMap, HashMap<String, String> idValue) {
        this.context = context;
        this.holderMap = holderMap;
        this.idValue = idValue;
        ids = new ArrayList<>(idValue.keySet());
    }

    @NonNull
    @Override
    public WatcherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_watcher, viewGroup, false);
        return new WatcherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WatcherHolder watcherHolder, int i) {
        watcherHolder.tvName.setText(idValue.get(ids.get(i)));
        CurrencyData data = holderMap.get(ids.get(i));
        watcherHolder.tvUsd.setText(String.format("%s", data.getUsd()));
        watcherHolder.tvInr.setText(String.format("%s", data.getInr()));
    }

    @Override
    public int getItemCount() {
        return ids == null ? 0 : ids.size();
    }

    class WatcherHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvUsd, tvInr;

        WatcherHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.row_watcher_tv_coin_name);
            tvUsd = itemView.findViewById(R.id.row_tv_usd);
            tvInr = itemView.findViewById(R.id.row_tv_inr);
        }
    }
}
