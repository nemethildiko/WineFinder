package com.example.winefinder.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.winefinder.R;
import com.example.winefinder.model.WineDto;

import java.util.ArrayList;
import java.util.List;

public class WineAdapter extends RecyclerView.Adapter<WineAdapter.WineViewHolder> {

    private final List<WineDto> items = new ArrayList<>();

    // ✅ Üres konstruktor (Fragment így tudja: new WineAdapter(new ArrayList<>()) helyett simán new WineAdapter())
    public WineAdapter() {}

    // ✅ Ha mégis listával akarod indítani:
    public WineAdapter(List<WineDto> initial) {
        if (initial != null) items.addAll(initial);
    }

    // ✅ Ez passzol a Fragmenthez: adapter.updateData(adatok)
    public void updateData(List<WineDto> newItems) {
        items.clear();
        if (newItems != null) items.addAll(newItems);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wine, parent, false);
        return new WineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WineViewHolder holder, int position) {
        WineDto w = items.get(position);

        // Null-safe (nehogy "null" jelenjen meg)
        String title = (w.getWine() != null) ? w.getWine() : "";
        String subtitle = (w.getWinery() != null) ? w.getWinery() : "";

        holder.title.setText(title);
        holder.subtitle.setText(subtitle);

        // Ha van rating TextView (nem kötelező), akkor kitöltjük
        if (holder.rating != null && w.getRating() != null) {
            holder.rating.setText("⭐ " + w.getRating().getAverage() + " (" + w.getRating().getReviews() + ")");
        }

        Glide.with(holder.itemView.getContext())
                .load(w.getImage())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class WineViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, subtitle;
        TextView rating;

        public WineViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.wineImage);
            title = itemView.findViewById(R.id.wineTitle);
            subtitle = itemView.findViewById(R.id.wineSubtitle);



        }
    }
}
