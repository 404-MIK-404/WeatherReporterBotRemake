package com.telegram.weatherreporterbotremaker.domain.repository;


import com.telegram.weatherbot.data.entity.FavoritesCity;
import com.telegram.weatherbot.data.model.WeatherRequest;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.IOException;
import java.util.List;


public interface WeatherRepository {

    WeatherRequest findInfoWeatherByCity(String city) throws IOException;

   List<FavoritesCity> getListFavoriteCity(User user);

   SendMessage saveCityToFavorite(SendMessage message,String city, User user);

   SendMessage removeCityFromFavorite(SendMessage message,String city,User user);

}
