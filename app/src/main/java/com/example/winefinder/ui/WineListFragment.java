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

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WineListFragment extends Fragment {

    private RecyclerView recyclerView;
    private WineAdapter adapter;

    private static final String ARG_TYPE = "wine_type";

    public static WineListFragment newInstance(String type) {
        WineListFragment f = new WineListFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TYPE, type);
        f.setArguments(b);
        return f;
    }

    public WineListFragment() {}

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

        // 🔥 CLICK → open details
        adapter.setOnWineClickListener(wine -> {
            Intent intent = new Intent(requireContext(), WineDetailActivity.class);

            intent.putExtra("wine", wine.getWine());
            intent.putExtra("winery", wine.getWinery());
            intent.putExtra("location", wine.getLocation());
            intent.putExtra("image", wine.getImage());

            startActivity(intent);
        });

        // 🔥 Hívjuk meg a letöltést
        String type = getArguments() != null ? getArguments().getString(ARG_TYPE, "reds") : "reds";
        fetchWines(type);

        return view;
    }

    // ⬇⬇⬇ EZ A METÓDUS NEM LEHET AZ onCreateView BELSŐ TERÜLETÉN
    private void fetchWines(String type) {
        WineApi api = ApiClient.getInstance().create(WineApi.class);

        api.getWinesByType(type).enqueue(new Callback<List<WineDto>>() {
            @Override
            public void onResponse(Call<List<WineDto>> call, Response<List<WineDto>> response) {
                Log.d("BOR_DEBUG", "URL: " + call.request().url());
                Log.d("BOR_DEBUG", "CODE: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    adapter.updateData(response.body());
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
