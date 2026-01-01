package com.example.winefinder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winefinder.R;
import com.example.winefinder.model.Wine;

import java.util.List;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.WineViewHolder> {

    private final List<Wine> wines;

    public WineAdapter(List<Wine> wines) {
        this.wines = wines;
    }

    @NonNull
    @Override
    public WineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wine, parent, false);
        return new WineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WineViewHolder holder, int position) {
        Wine wine = wines.get(position);
        holder.tvName.setText(wine.getName());
        holder.tvMeta.setText(wine.getCountry() + " • " + wine.getYear());
    }

    @Override
    public int getItemCount() {
        return wines.size();
    }

    static class WineViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvMeta;

        WineViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvMeta = itemView.findViewById(R.id.tvMeta);
        }
    }
}
