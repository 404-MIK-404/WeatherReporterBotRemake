package com.telegram.weatherreporterbotremaker.data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;


@Data
public class WeatherForecast {

    @SerializedName("forecastday")
    @Expose
    private List<WeatherForecastDay> weatherForecastDay;

}
