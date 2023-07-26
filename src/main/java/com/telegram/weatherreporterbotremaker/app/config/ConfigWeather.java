package com.telegram.weatherreporterbotremaker.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
@Data
public class ConfigWeather {

    @Value("${weather.key.api}")
    private String api;


}
