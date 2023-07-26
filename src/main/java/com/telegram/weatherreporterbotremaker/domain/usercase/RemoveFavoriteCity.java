package com.telegram.weatherreporterbotremaker.domain.usercase;

import com.telegram.weatherbot.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
@AllArgsConstructor
public class RemoveFavoriteCity {

    private final WeatherRepository weatherRepository;

    public SendMessage removeCityFromFavoriteList(SendMessage message,String city, User user){
        return weatherRepository.removeCityFromFavorite(message,city,user);
    }

}
