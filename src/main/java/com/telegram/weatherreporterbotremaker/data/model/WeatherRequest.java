package com.telegram.weatherreporterbotremaker.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeatherRequest {

    @SerializedName("current")
    @Expose
    private WeatherMainInfo weatherMainInfo;

    @SerializedName("location")
    @Expose
    private WeatherLocation weatherLocation;

    @SerializedName("forecast")
    @Expose
    private WeatherForecast weatherForecast;

}
