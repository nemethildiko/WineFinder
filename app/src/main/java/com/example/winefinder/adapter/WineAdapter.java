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

    // 🔥 CLICK LISTENER interface
    public interface OnWineClickListener {
        void onWineClick(WineDto wine);
    }

    private OnWineClickListener listener;

    public void setOnWineClickListener(OnWineClickListener listener) {
        this.listener = listener;
    }

    // Constructors
    public WineAdapter() {}

    public WineAdapter(List<WineDto> initial) {
        if (initial != null) items.addAll(initial);
    }

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

        // Eredeti logika (kattintás a teljes sorra a részletekhez)
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onWineClick(w);
        });

        // Szövegek és fő kép betöltése
        holder.title.setText(w.getWine() != null ? w.getWine() : "");
        holder.subtitle.setText(w.getWinery() != null ? w.getWinery() : "");

        Glide.with(holder.itemView.getContext())
                .load(w.getImage())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.image);

        // ⭐ KEDVENC (CSILLAG) LOGIKA ⭐

        // 1. Ikon beállítása: Csillag
        if (w.isFavorite()) {
            // Teli sárga csillag (beépített Android forrás)
            holder.btnFavorite.setImageResource(android.R.drawable.star_on);
        } else {
            // Üres/szürke csillag (beépített Android forrás)
            holder.btnFavorite.setImageResource(android.R.drawable.star_off);
        }

        // 2. Kattintás a csillagra
        holder.btnFavorite.setOnClickListener(v -> {
            // Állapot megfordítása (true -> false, vagy false -> true)
            boolean newState = !w.isFavorite();
            w.setFavorite(newState);

            // Adapter frissítése az adott pozíción, hogy átváltson a kép
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ... a fájl többi része változatlan ...

    static class WineViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, subtitle;

        // 1. LÉPÉS: Add hozzá ezt a sort!
        ImageView btnFavorite;

        public WineViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.wineImage);
            title = itemView.findViewById(R.id.wineTitle);
            subtitle = itemView.findViewById(R.id.wineSubtitle);

            // 2. LÉPÉS: Keresd meg a gombot az ID alapján!
            // (Feltételezve, hogy az item_wine.xml-ben a gomb ID-ja: btnFavorite)
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}
