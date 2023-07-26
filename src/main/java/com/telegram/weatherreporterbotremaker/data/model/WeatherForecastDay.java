package com.telegram.weatherreporterbotremaker.data.model;


import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeatherForecastDay {

    @SerializedName("date")
    private String date;

    @SerializedName("day")
    private WeatherForecastTomorrow weatherForecastTomorrow;


}
