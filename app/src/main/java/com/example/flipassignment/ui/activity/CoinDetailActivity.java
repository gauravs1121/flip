package com.example.flipassignment.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flipassignment.R;
import com.example.flipassignment.holder.CoinDetailHolder;
import com.example.flipassignment.network.APICall;
import com.example.flipassignment.network.APIConfiguration;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinDetailActivity extends AppCompatActivity {
    ProgressDialog dialog;
    TextView tvSymbol, tvName, tvLastUpdated;
    ImageView ivCoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        String id = getIntent().getStringExtra("id");
        Log.e("DB", id);
        tvLastUpdated = findViewById(R.id.act_detail_tv_lastupdated);
        tvSymbol = findViewById(R.id.act_detail_tv_symbol);
        tvName = findViewById(R.id.act_detail_tv_name);
        ivCoin = findViewById(R.id.act_detail_iv);
        dialog = new ProgressDialog(this);
        getCoinDetail(id);
    }

    private void getCoinDetail(String id) {
        dialog.show();
        APICall apiCall = APIConfiguration.getClient().create(APICall.class);

        Call<CoinDetailHolder> call =  apiCall.getCoinDetail(id);

        call.enqueue(new Callback<CoinDetailHolder>() {
            @Override
            public void onResponse(Call<CoinDetailHolder> call, Response<CoinDetailHolder> response) {
                dialog.dismiss();
                CoinDetailHolder holder =response.body();
                setData(holder);
            }

            @Override
            public void onFailure(Call<CoinDetailHolder> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void setData(CoinDetailHolder holder) {
        tvName.setText(String.format("Name : %s", holder.getName()));
        tvSymbol.setText(String.format("Symbol : %s", holder.getSymbol()));
        tvLastUpdated.setText(String.format("Last Updated : %s", holder.getLastUpdated()));
        Picasso.get()
                .load(holder.getImage().getLarge())
                .into(ivCoin);

    }
}
