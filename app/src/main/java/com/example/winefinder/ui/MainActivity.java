package com.example.winefinder.ui;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.winefinder.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);

        // első képernyő: Reds
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new WineListFragment("reds"))
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_reds) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new WineListFragment("reds"))
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.nav_whites) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new WineListFragment("whites"))
                        .commit();
                return true;
            } else if (item.getItemId() == R.id.nav_favorites) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new FavoritesFragment())
                        .commit();
                return true;
            }
            return false;
        });
    }
}
