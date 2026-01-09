package com.example.winefinder.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.R;
import com.example.winefinder.adapter.WineAdapter;
import com.example.winefinder.model.WineDto;
import com.example.winefinder.network.ApiClient;
import com.example.winefinder.network.WineApi;
import com.google.gson.JsonElement;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WineListFragment extends Fragment {

    private RecyclerView recyclerView;
    private WineAdapter adapter;

    private static final String ARG_TYPE = "wine_type";
    // Fragment létrehozásakor meghívódik ez a függvény
    public static WineListFragment newInstance(String type) {
        WineListFragment f = new WineListFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TYPE, type);
        f.setArguments(b);
        return f;
    }
    // Fragment létrehozásakor meghívódik ez a függvény
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_wine_list, container, false);

        recyclerView = view.findViewById(R.id.wineRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        adapter = new WineAdapter();
        recyclerView.setAdapter(adapter);

        adapter.setOnWineClickListener(wine -> {
            Intent intent = new Intent(requireContext(), WineDetailActivity.class);
            intent.putExtra("wine_object", wine);
            startActivity(intent);
        });

        String type = getArguments() != null
                ? getArguments().getString(ARG_TYPE, "reds")
                : "reds";

        fetchWines(type);
        return view;
    }

    private void fetchWines(String type) {
        WineApi api = ApiClient.getInstance().create(WineApi.class);
        // Hívás az API-ból
        api.getWinesByType(type).enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call,
                                   Response<JsonElement> response) {

                Log.d("BOR_DEBUG", "URL: " + call.request().url());
                Log.d("BOR_DEBUG", "CODE: " + response.code());

                if (!response.isSuccessful() || response.body() == null) {
                    Log.e("BOR_DEBUG", "Üres vagy sikertelen válasz");
                    return;
                }

                Gson gson = new Gson();
                JsonElement root = response.body();
                List<WineDto> wines = new ArrayList<>();

                if (root.isJsonArray()) {
                    wines = Arrays.asList(
                            gson.fromJson(root, WineDto[].class)
                    );
                } else {
                    wines.add(gson.fromJson(root, WineDto.class));
                }

                adapter.updateData(wines);
            }
            // Hívás sikertelen esetén
            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("BOR_DEBUG", "FAIL", t);
            }
        });
    }
}
