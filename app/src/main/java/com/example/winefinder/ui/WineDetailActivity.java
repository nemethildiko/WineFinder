package com.example.winefinder.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.winefinder.R;
import com.example.winefinder.model.WineDto;

public class WineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wine_detail);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());

        // View-k az XML ID-k alapján
        ImageView image = findViewById(R.id.detailImage);
        TextView title = findViewById(R.id.detailTitle);
        TextView winery = findViewById(R.id.detailWinery);
        TextView location = findViewById(R.id.detailLocation);

        // Objektum átvétele
        WineDto wine = (WineDto) getIntent().getSerializableExtra("wine_object");

        if (wine == null) {
            title.setText("Hiba történt");
            return;
        }

        // Szövegek (null-safe)
        title.setText(wine.getWine() != null ? wine.getWine() : "Nincs név");
        winery.setText(wine.getWinery() != null ? wine.getWinery() : "Nincs pincészet");
        location.setText(wine.getLocation() != null ? wine.getLocation() : "Nincs helyadat");

        // Kép
        Glide.with(this)
                .load(wine.getImage())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(image);
    }
}
