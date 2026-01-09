package com.example.winefinder.network;

import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WineApi {

    @GET("wines/{type}")
    Call<JsonElement> getWinesByType(@Path("type") String type);
}
