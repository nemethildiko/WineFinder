package com.example.winefinder.data;

import com.example.winefinder.model.WineDto;
import java.util.ArrayList;
import java.util.List;

public class WineRepository {

    private static WineRepository instance;
    private final List<WineDto> wines = new ArrayList<>();

    private WineRepository() {}

    public static WineRepository getInstance() {
        if (instance == null) {
            instance = new WineRepository();
        }
        return instance;
    }

    public List<WineDto> getAllWines() {
        return wines;
    }

    public void setWines(List<WineDto> newWines) {
        wines.clear();
        if (newWines != null) {
            wines.addAll(newWines);
        }
    }
}
