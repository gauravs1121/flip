package com.example.flipassignment.ui.fragment;

import android.app.DatePickerDialog;
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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.flipassignment.AppRepository;
import com.example.flipassignment.FlipAssignment;
import com.example.flipassignment.PrefUtils;
import com.example.flipassignment.R;
import com.example.flipassignment.database.entity.EventEntity;
import com.example.flipassignment.holder.EventHolder;
import com.example.flipassignment.network.APICall;
import com.example.flipassignment.network.APIConfiguration;
import com.example.flipassignment.ui.adapter.EventAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventFragment extends Fragment {

    public static final String TAG = "Event";

    public EventFragment() {
        // Required empty public constructor
    }

    ViewGroup container;
    EditText etDate;
    Calendar calendar;
    private Button buttonUpdate;
    private String endDate;
    private AppRepository mRepository;
    ProgressDialog dialog;
    List<EventEntity> holderList = new ArrayList<>();
    EventAdapter adapter;
    RecyclerView rvEvents;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Gson gson;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        return inflater.inflate(R.layout.fragment_event, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.loading));
        etDate = container.findViewById(R.id.et_dtPicker);
        calendar = Calendar.getInstance();
        buttonUpdate = container.findViewById(R.id.button_update);
        rvEvents = container.findViewById(R.id.rv_event);
        rvEvents.setLayoutManager(new LinearLayoutManager(getContext()));

        mRepository = new AppRepository(FlipAssignment.getInstance());
        gson = new GsonBuilder().create();
        holderList = mRepository.getAllEvents();
        if (holderList.size() > 0) {
            etDate.setText(PrefUtils.getStartDate(getContext()));
            endDate = PrefUtils.getEndDate(getContext());
        }

        mSwipeRefreshLayout = container.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (etDate.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Select start date!", Toast.LENGTH_LONG).show();
                else getEvents();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        adapter = new EventAdapter(getContext(), holderList);
        rvEvents.setAdapter(adapter);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etDate.getText().toString().isEmpty())
                    Toast.makeText(getContext(), "Select start date!", Toast.LENGTH_LONG).show();
                else getEvents();
            }
        });

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void getEvents() {
        dialog.show();
        APICall apiCall =
                APIConfiguration.getClient().create(APICall.class);


        Call<EventHolder> call = apiCall.getEvent(etDate.getText().toString(), endDate);
        Log.e(TAG, call.request().url().toString());
        call.enqueue(new Callback<EventHolder>() {
            @Override
            public void onResponse(@NonNull Call<EventHolder> call, @NonNull Response<EventHolder> response) {
//
                holderList = response.body().getData();
                mRepository.deleteEvent();
                mRepository.insertEvent(holderList);
                PrefUtils.saveStartDate(getContext(), etDate.getText().toString());
                PrefUtils.saveEndDate(getContext(), endDate);

                adapter = new EventAdapter(getContext(), holderList);
                rvEvents.setAdapter(adapter);

                dialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<EventHolder> call, @NonNull Throwable t) {
                dialog.dismiss();
            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String format = "yyyy-MM-dd"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);


            etDate.setText(sdf.format(calendar.getTime()));

            calendar.add(Calendar.DATE, 5);
            Date resultdate = new Date(calendar.getTimeInMillis());
            endDate = sdf.format(resultdate);
            calendar.add(Calendar.DATE, -5);
            Log.e("Event", monthOfYear + " " + endDate);

        }

    };
}
