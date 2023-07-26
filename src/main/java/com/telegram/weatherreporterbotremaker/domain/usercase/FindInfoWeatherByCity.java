package com.telegram.weatherreporterbotremaker.domain.usercase;


import com.telegram.weatherbot.data.model.WeatherRequest;
import com.telegram.weatherbot.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
public class FindInfoWeatherByCity {

    private final WeatherRepository weatherRepository;


    public WeatherRequest execute(String city) throws IOException  {
        return weatherRepository.findInfoWeatherByCity(city);
    }

}
