package com.example.winefinder;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_detail);

        TextView tvName = findViewById(R.id.tvDetailName);
        TextView tvMeta = findViewById(R.id.tvDetailMeta);

        String name = getIntent().getStringExtra("name");
        String country = getIntent().getStringExtra("country");
        int year = getIntent().getIntExtra("year", 0);

        tvName.setText(name);
        tvMeta.setText(country + " • " + year);
    }
}
