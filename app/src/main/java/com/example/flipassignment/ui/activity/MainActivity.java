package com.example.flipassignment.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.flipassignment.R;
import com.example.flipassignment.ui.fragment.CoinFragment;
import com.example.flipassignment.ui.fragment.EventFragment;
import com.example.flipassignment.ui.fragment.WatcherFragment;

public class MainActivity extends AppCompatActivity {

    ActionBar actionBar;
    Fragment selectedFragment;
    final FragmentManager fragmentManager = getSupportFragmentManager();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    if (actionBar != null)
                        actionBar.setTitle(getString(R.string.coins));
                    selectedFragment = new CoinFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    if (actionBar != null)
                        actionBar.setTitle(getString(R.string.watcher));
                    selectedFragment = new WatcherFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit();
                    return true;
                case R.id.navigation_notifications:
                    if (actionBar != null)
                        actionBar.setTitle(getString(R.string.events));
                    selectedFragment = new EventFragment();
                    fragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(getString(R.string.coins));
        selectedFragment = new CoinFragment();
        fragmentManager.beginTransaction().replace(R.id.container, selectedFragment).commit();

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
