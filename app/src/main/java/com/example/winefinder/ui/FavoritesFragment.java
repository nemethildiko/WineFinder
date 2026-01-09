package com.example.winefinder.ui;

import android.os.Bundle;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.R;
import com.example.winefinder.adapter.WineAdapter;
import com.example.winefinder.data.AppDatabase;
import com.example.winefinder.data.entity.FavoriteWineEntity;
import com.example.winefinder.model.WineDto;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {
    // Fragment létrehozásakor meghívódik ez a függvény
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



        AppDatabase db = AppDatabase.getInstance(requireContext());
        List<FavoriteWineEntity> favoriteEntities =
                db.favoriteWineDao().getAll();

        // Átalakítás FavoriteWineEntity → WineDto
        List<WineDto> favorites = new ArrayList<>();

        for (FavoriteWineEntity e : favoriteEntities) {
            WineDto w = new WineDto();
            w.setFavorite(true);

            try {
                java.lang.reflect.Field wineField = WineDto.class.getDeclaredField("wine");
                java.lang.reflect.Field wineryField = WineDto.class.getDeclaredField("winery");
                java.lang.reflect.Field imageField = WineDto.class.getDeclaredField("image");

                wineField.setAccessible(true);
                wineryField.setAccessible(true);
                imageField.setAccessible(true);

                wineField.set(w, e.wineName);
                wineryField.set(w, e.winery);
                imageField.set(w, e.image);

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            favorites.add(w);
        }

        adapter.updateData(favorites);

        return view;
    }
}
