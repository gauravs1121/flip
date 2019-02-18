package com.example.flipassignment.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flipassignment.AppRepository;
import com.example.flipassignment.R;
import com.example.flipassignment.database.entity.CoinEntity;
import com.example.flipassignment.database.entity.WatcherEntity;
import com.example.flipassignment.ui.activity.CoinDetailActivity;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.CoinViewHolder> {
    private Context context;
    private List<CoinEntity> holders;
    private AppRepository mRepository;
    private AlertDialog.Builder builder;

    public CoinAdapter(Context context, List<CoinEntity> holderList, AppRepository mRepository) {
        this.context = context;
        this.holders = holderList;
        this.mRepository = mRepository;
    }

    @NonNull
    @Override
    public CoinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int pos) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_coin, parent, false);
        return new CoinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinViewHolder viewHolder, int pos) {
        CoinEntity holder = holders.get(pos);
        viewHolder.coinName.setText(holder.getName());
        viewHolder.coinSymbol.setText(holder.getSymbol());
    }

    @Override
    public int getItemCount() {
        return holders == null ? 0 : holders.size();

    }

    class CoinViewHolder extends RecyclerView.ViewHolder {
        TextView coinName, coinSymbol;
        ImageView addWatcher;

        CoinViewHolder(@NonNull View itemView) {
            super(itemView);
            coinName = itemView.findViewById(R.id.tvCoinName);
            coinSymbol = itemView.findViewById(R.id.tvCoinSymbol);
            addWatcher = itemView.findViewById(R.id.ivAddWatcher);


            addWatcher.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder = new AlertDialog.Builder(context);

                    builder.setTitle("Add to Watcher")
                            .setMessage("Are you sure you want to add " + holders.get(getAdapterPosition()).getName() + " coin?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            WatcherEntity watcherEntity = new WatcherEntity(holders.get(getAdapterPosition()).getId(),
                                    holders.get(getAdapterPosition()).getName());
                            mRepository.insert(watcherEntity);

                        }
                    });
                    builder.show();
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CoinDetailActivity.class);
                    intent.putExtra("id", holders.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
