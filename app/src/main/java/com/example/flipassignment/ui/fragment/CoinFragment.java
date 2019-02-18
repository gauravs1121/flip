package com.example.flipassignment.ui.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flipassignment.AppRepository;
import com.example.flipassignment.FlipAssignment;
import com.example.flipassignment.R;
import com.example.flipassignment.database.entity.CoinEntity;
import com.example.flipassignment.network.APICall;
import com.example.flipassignment.network.APIConfiguration;
import com.example.flipassignment.ui.adapter.CoinAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoinFragment extends Fragment {


    public CoinFragment() {
        // Required empty public constructor
    }

    public static final String TAG = "Coin";
    ViewGroup container;
    ProgressDialog dialog;
    List<CoinEntity> holderList = new ArrayList<>();
    RecyclerView rvCoins;
    CoinAdapter adapter;
    private AppRepository mRepository;
    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        return inflater.inflate(R.layout.fragment_coin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.loading));
        mRepository = new AppRepository(FlipAssignment.getInstance());
        rvCoins = container.findViewById(R.id.rv_coin_list);
        rvCoins.setLayoutManager(new LinearLayoutManager(getContext()));
        holderList = mRepository.getmAllCoins();
        if (holderList.size() == 0)
            getCoinList();
        adapter = new CoinAdapter(getContext(), holderList, mRepository);


        mSwipeRefreshLayout = container.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getCoinList();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        rvCoins.setAdapter(adapter);

    }

    private void getCoinList() {
        dialog.show();
        APICall apiCall =
                APIConfiguration.getClient().create(APICall.class);


        Call<List<CoinEntity>> call = apiCall.getCoinList();
        call.enqueue(new Callback<List<CoinEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<CoinEntity>> call, @NonNull Response<List<CoinEntity>> response) {

                holderList = response.body();
                adapter = new CoinAdapter(getContext(), holderList, mRepository);
                rvCoins.setAdapter(adapter);
                mRepository.deleteCoin();
                mRepository.insert(holderList);
//                holderList.clear();

                dialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<List<CoinEntity>> call, @NonNull Throwable t) {
                dialog.dismiss();
            }
        });
    }
}
