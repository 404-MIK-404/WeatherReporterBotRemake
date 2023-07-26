package com.telegram.weatherreporterbotremaker.data.repository;


import com.telegram.weatherbot.data.model.WeatherRequest;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface RetroServiceInterface {

    @GET(value = "forecast.json")
    Call<WeatherRequest> getInfoWeather(@Query("key") String API_KEY, @Query("q") String city, @Query("days") Integer days);

}
