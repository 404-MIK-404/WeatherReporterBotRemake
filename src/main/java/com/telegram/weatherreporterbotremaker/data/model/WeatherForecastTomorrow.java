package com.telegram.weatherreporterbotremaker.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class WeatherForecastTomorrow {

    @SerializedName("maxtemp_c")
    @Expose
    private Double maxTempC;

    @SerializedName("mintemp_c")
    @Expose
    private Double minTempC;

    @SerializedName("avgtemp_c")
    @Expose
    private Double avgTempC;

    @SerializedName("maxwind_kph")
    @Expose
    private Double maxWindKph;

    @SerializedName("uv")
    @Expose
    private Double uv;

    @SerializedName("avghumidity")
    @Expose
    private Double avgHumidity;




}
