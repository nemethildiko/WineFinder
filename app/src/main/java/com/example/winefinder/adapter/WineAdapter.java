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
import com.example.winefinder.data.AppDatabase;
import com.example.winefinder.data.dao.FavoriteWineDao;
import com.example.winefinder.data.entity.FavoriteWineEntity;





import java.util.ArrayList;
import java.util.List;
// WineAdapter.java
public class WineAdapter extends RecyclerView.Adapter<WineAdapter.WineViewHolder> {

    private final List<WineDto> items = new ArrayList<>();


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
    // Methods
    @NonNull
    @Override
    public WineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wine, parent, false);
        return new WineViewHolder(v);
    }
    // Methods
    @Override
    public void onBindViewHolder(@NonNull WineViewHolder holder, int position) {
        WineDto w = items.get(position);

        // kattintás
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onWineClick(w);
        });

        // szöveg
        holder.title.setText(w.getWine() != null ? w.getWine() : "");
        holder.subtitle.setText(w.getWinery() != null ? w.getWinery() : "");

        Glide.with(holder.itemView.getContext())
                .load(w.getImage())
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .into(holder.image);

        // room logika

        AppDatabase db = AppDatabase.getInstance(holder.itemView.getContext());
        FavoriteWineDao dao = db.favoriteWineDao();

        boolean isFavorite = dao.isFavorite(w.getWine());

        // Csillag
        if (isFavorite) {
            holder.btnFavorite.setImageResource(android.R.drawable.star_on);
        } else {
            holder.btnFavorite.setImageResource(android.R.drawable.star_off);
        }

        // Csillag kattintás
        holder.btnFavorite.setOnClickListener(v -> {

            if (dao.isFavorite(w.getWine())) {
                // törlés
                dao.deleteByName(w.getWine());
                holder.btnFavorite.setImageResource(android.R.drawable.star_off);
            } else {
                // beszúrás
                FavoriteWineEntity entity = new FavoriteWineEntity();
                entity.wineName = w.getWine();
                entity.winery = w.getWinery();
                entity.image = w.getImage();

                dao.insert(entity);
                holder.btnFavorite.setImageResource(android.R.drawable.star_on);
            }
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    // ViewHolder
    static class WineViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title, subtitle;


        ImageView btnFavorite;
        // Constructor
        public WineViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.wineImage);
            title = itemView.findViewById(R.id.wineTitle);
            subtitle = itemView.findViewById(R.id.wineSubtitle);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
        }
    }
}
