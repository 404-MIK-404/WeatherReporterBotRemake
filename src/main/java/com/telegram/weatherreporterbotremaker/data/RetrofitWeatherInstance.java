package com.telegram.weatherreporterbotremaker.data;


import com.telegram.weatherbot.app.config.ConfigWeather;
import com.telegram.weatherbot.data.model.WeatherRequest;
import com.telegram.weatherbot.data.repository.RetroServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class RetrofitWeatherInstance {

    private final ConfigWeather configWeather;

    private Retrofit requestWeather;

    private RetroServiceInterface retroServiceWeather;

    private final String BASE_URL = "https://api.weatherapi.com/v1/";


    @PostConstruct
    private void init(){
        this.requestWeather = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retroServiceWeather = requestWeather.create(RetroServiceInterface.class);
    }

    public WeatherRequest findWeatherByCity(String city) throws IOException {
        Call<WeatherRequest> resultQuery = retroServiceWeather.getInfoWeather(configWeather.getApi(), city,2);
        return resultQuery.execute().body();
    }


}
