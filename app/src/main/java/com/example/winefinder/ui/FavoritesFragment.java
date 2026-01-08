package com.example.winefinder.ui;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.R;
import com.example.winefinder.adapter.WineAdapter;
import com.example.winefinder.model.WineDto;
import com.example.winefinder.data.WineRepository;


import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        WineAdapter adapter = new WineAdapter();
        recyclerView.setAdapter(adapter);

        // ⭐ CSAK EZ AZ ÚJ LOGIKA
        List<WineDto> allWines = WineRepository.getInstance().getAllWines();
        List<WineDto> favorites = new ArrayList<>();

        for (WineDto w : allWines) {
            if (w.isFavorite()) {
                favorites.add(w);
            }
        }

        adapter.updateData(favorites);

        return view;
    }
}
