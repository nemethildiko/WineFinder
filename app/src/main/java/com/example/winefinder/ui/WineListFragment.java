package com.example.winefinder.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.R;
import com.example.winefinder.adapter.WineAdapter;
import com.example.winefinder.model.WineDto;
import com.example.winefinder.network.ApiClient;
import com.example.winefinder.network.WineApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WineListFragment extends Fragment {

    private RecyclerView recyclerView;
    private WineAdapter adapter;

    private static final String DEFAULT_TYPE = "reds";

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

        fetchWines(DEFAULT_TYPE);

        return view;
    }

    private void fetchWines(String type) {
        WineApi api = ApiClient.getInstance().create(WineApi.class);

        api.getWinesByType(type).enqueue(new Callback<List<WineDto>>() {
            @Override
            public void onResponse(Call<List<WineDto>> call, Response<List<WineDto>> response) {
                Log.d("BOR_DEBUG", "URL: " + call.request().url());
                Log.d("BOR_DEBUG", "CODE: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    List<WineDto> wines = response.body();
                    Log.d("BOR_DEBUG", "DARAB: " + wines.size());

                    adapter.updateData(wines);
                } else {
                    Log.e("BOR_DEBUG", "Nem sikeres válasz vagy üres body.");
                }
            }

            @Override
            public void onFailure(Call<List<WineDto>> call, Throwable t) {
                Log.e("BOR_DEBUG", "FAIL: " + t.getMessage(), t);
            }
        });
    }
}
