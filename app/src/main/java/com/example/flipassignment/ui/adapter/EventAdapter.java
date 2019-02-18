package com.example.flipassignment.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flipassignment.R;
import com.example.flipassignment.database.entity.EventEntity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    Context context;
    List<EventEntity> holderList;
    public EventAdapter(Context context, List<EventEntity> holderList) {
        this.context =context;
        this.holderList = holderList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_event, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EventEntity eventEntity =  holderList.get(i);
        Picasso.get().load(eventEntity.getScreenshot()).into(viewHolder.ivScreenShot);
        viewHolder.tvTitle.setText(eventEntity.getTitle());
        viewHolder.tvType.setText(eventEntity.getType());
        Log.e("DB", eventEntity.getStart_date() + " " + eventEntity.getEnd_date());
        viewHolder.tvStart.setText(eventEntity.getStart_date());
        viewHolder.tvEnd.setText(eventEntity.getEnd_date());
        String address = eventEntity.getVenue()+ " " + eventEntity.getAddress() + " " + eventEntity.getCity() + " " + eventEntity.getCountry();
        viewHolder.tvAddress.setText(address.trim());

    }

    @Override
    public int getItemCount() {
        return holderList == null ? 0 : holderList.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvType, tvTitle, tvStart, tvEnd, tvAddress;
        ImageView ivScreenShot;
         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             tvType = itemView.findViewById(R.id.tv_type);
             tvTitle = itemView.findViewById(R.id.tv_title);
             tvStart = itemView.findViewById(R.id.tv_startDate);
             tvEnd = itemView.findViewById(R.id.tv_endDate);
             tvAddress = itemView.findViewById(R.id.tv_address);
             ivScreenShot = itemView.findViewById(R.id.iv_screenshot);
         }
     }
}
