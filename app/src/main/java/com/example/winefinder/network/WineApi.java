package com.example.winefinder.network;

import com.example.winefinder.model.WineDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WineApi {

    // pl: wines/reds, wines/whites, wines/sparkling...
    @GET("wines/{type}")
    Call<List<WineDto>> getWinesByType(@Path("type") String type);
}
