package com.example.flipassignment.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.flipassignment.R;
import com.example.flipassignment.holder.CoinDetailHolder;
import com.example.flipassignment.network.APICall;
import com.example.flipassignment.network.APIConfiguration;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoinDetailActivity extends AppCompatActivity {
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_detail);
        String id = getIntent().getStringExtra("id");
        Log.e("DB", id);
        dialog = new ProgressDialog(this);
        getCoinDetail(id);
    }

    private void getCoinDetail(String id) {
        dialog.show();
        APICall apiCall = APIConfiguration.getClient().create(APICall.class);

        Call<CoinDetailHolder> call =  apiCall.getCoinDetail(id);
        Log.e("DB", call.request().url().toString());
        call.enqueue(new Callback<CoinDetailHolder>() {
            @Override
            public void onResponse(Call<CoinDetailHolder> call, Response<CoinDetailHolder> response) {
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CoinDetailHolder> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
