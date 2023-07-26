package com.telegram.weatherreporterbotremaker.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeatherMainInfo {

    @SerializedName("temp_c")
    @Expose
    private Double tempC;

    @SerializedName("wind_kph")
    @Expose
    private Double windKph;

    @SerializedName("humidity")
    @Expose
    private Double humidity;

    @SerializedName("feelslike_c")
    @Expose
    private Double feelsLikeC;

    @SerializedName("pressure_mb")
    @Expose
    private Double pressureMb;

    @SerializedName("uv")
    @Expose
    private Double uv;

    @SerializedName("gust_kph")
    @Expose
    private Double gustKph;

}
