package com.telegram.weatherreporterbotremaker.domain.usercase;

import com.telegram.weatherbot.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
@AllArgsConstructor
public class AddFavoriteCity {

    private final WeatherRepository weatherRepository;

    public SendMessage resultAddCityToFavorite(SendMessage message,String city, User user){
        return weatherRepository.saveCityToFavorite(message,city,user);
    }

}
