package com.example.winefinder.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.winefinder.R;

public class WineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ✅ 1. Layout betöltése ELŐSZÖR
        setContentView(R.layout.activity_wine_detail);

        // ✅ 2. Toolbar beállítása
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // ✅ 3. Nézetek
        ImageView image = findViewById(R.id.detailImage);
        TextView title = findViewById(R.id.detailTitle);
        TextView winery = findViewById(R.id.detailWinery);
        TextView location = findViewById(R.id.detailLocation);

        // ✅ 4. Intent adatok
        String wine = getIntent().getStringExtra("wine");
        String wineryName = getIntent().getStringExtra("winery");
        String locationText = getIntent().getStringExtra("location");
        String imageUrl = getIntent().getStringExtra("image");

        title.setText(wine);
        winery.setText(wineryName);
        location.setText(locationText);

        Glide.with(this)
                .load(imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(image);
    }

    // 🔙 Vissza nyíl működtetése
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
