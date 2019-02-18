package com.example.flipassignment.ui.fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.flipassignment.AppRepository;
import com.example.flipassignment.FlipAssignment;
import com.example.flipassignment.R;
import com.example.flipassignment.SplashActivity;
import com.example.flipassignment.database.entity.WatcherEntity;
import com.example.flipassignment.holder.CurrencyData;
import com.example.flipassignment.network.APICall;
import com.example.flipassignment.network.APIConfiguration;
import com.example.flipassignment.ui.activity.MainActivity;
import com.example.flipassignment.ui.adapter.WatcherAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class WatcherFragment extends Fragment {


    public WatcherFragment() {
        // Required empty public constructor
    }

    public static final String TAG = "Watcher";

    ViewGroup container;
    RecyclerView rvWatcher;
    WatcherAdapter adapter;
    TextView tvErrorMsg;
    List<String> watcherIds = new ArrayList<>();
    List<WatcherEntity> watcherEntities =  new ArrayList<>();
    Map<String, CurrencyData> holderMap = new HashMap<>();
    private AppRepository mRepository;
    APICall apiCall;
    ProgressDialog dialog;
    Gson gson;
    private HashMap<String, String> idValue =  new HashMap<>();


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        return inflater.inflate(R.layout.fragment_watcher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRepository = new AppRepository(FlipAssignment.getInstance());
        rvWatcher = container.findViewById(R.id.rv_watcher);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.loading));
        tvErrorMsg = container.findViewById(R.id.tv_msgbox);
        apiCall = APIConfiguration.getClient().create(APICall.class);
        gson = new GsonBuilder().create();
        rvWatcher.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new WatcherAdapter(getContext(), holderMap, idValue);
        rvWatcher.setAdapter(adapter);
        getWatcher();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FlipAssignment.getInstance().getForegroundStatus()){
                    getWatcher();
                }

            }
        }, 1000*60);

    }

    private void getWatcher() {
        watcherIds = mRepository.geWatcherCoinIds();
        watcherEntities =  mRepository.geWatcherCoinData();

        if (watcherIds.size() > 0) {
            tvErrorMsg.setVisibility(View.GONE);
            for (WatcherEntity h :
                    watcherEntities) {
                idValue.put(h.getId(), h.getName());
            }
            getWatcherData();
        }
    }

    private void getWatcherData() {
        dialog.show();


        String ids = TextUtils.join(",", watcherIds);
        String currencies = "usd,inr";
        Call<Map<String, CurrencyData>> call = apiCall.getWatcherData(ids, currencies);
        Log.e(TAG, call.request().url().toString());
        call.enqueue(new Callback<Map<String, CurrencyData>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, CurrencyData>> call, @NonNull Response<Map<String, CurrencyData>> response) {
               holderMap = response.body();

                adapter = new WatcherAdapter(getContext(), holderMap, idValue);
                rvWatcher.setAdapter(adapter);
                String json = gson.toJson(holderMap);
                Log.e(TAG, json);
                dialog.dismiss();

            }

            @Override
            public void onFailure(@NonNull Call<Map<String, CurrencyData>> call, @NonNull Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
