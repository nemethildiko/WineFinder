package com.example.winefinder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.adapter.WineAdapter;
import com.example.winefinder.model.Wine;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvWines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvWines = findViewById(R.id.rvWines);

        // 1) Megmondjuk, hogy a RecyclerView függőleges listaként viselkedjen
        rvWines.setLayoutManager(new LinearLayoutManager(this));

        // 2) Ideiglenes adatok (később API-ból jönnek majd)
        List<Wine> wines = createDummyWines();

        // 3) Adapter: adat + sor layout összekötése
        WineAdapter adapter = new WineAdapter(wines, wine -> {
            Intent intent = new Intent(MainActivity.this, WineDetailActivity.class);
            intent.putExtra("name", wine.getName());
            intent.putExtra("country", wine.getCountry());
            intent.putExtra("year", wine.getYear());
            startActivity(intent);
        });
        rvWines.setAdapter(adapter);

    }

    private List<Wine> createDummyWines() {
        List<Wine> list = new ArrayList<>();
        list.add(new Wine("Tokaji Aszú", "Magyarország", 2016));
        list.add(new Wine("Egri Bikavér", "Magyarország", 2018));
        list.add(new Wine("Chianti Classico", "Olaszország", 2019));
        list.add(new Wine("Rioja Reserva", "Spanyolország", 2017));
        list.add(new Wine("Bordeaux", "Franciaország", 2015));
        return list;
    }
}
