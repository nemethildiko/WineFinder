package com.example.winefinder.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.winefinder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // Kezdő fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, WineListFragment.newInstance("reds"))
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_reds) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, WineListFragment.newInstance("reds"))
                        .commit();
                return true;
            }

            if (item.getItemId() == R.id.nav_whites) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, WineListFragment.newInstance("whites"))
                        .commit();
                return true;
            }

            if (item.getItemId() == R.id.nav_favorites) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, WineListFragment.newInstance("favorites"))
                        .commit();
                return true;
            }

            return false;
        });
    }
}
