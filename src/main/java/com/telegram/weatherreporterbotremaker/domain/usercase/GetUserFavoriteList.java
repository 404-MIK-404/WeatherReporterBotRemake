package com.telegram.weatherreporterbotremaker.domain.usercase;

import com.telegram.weatherbot.data.entity.FavoritesCity;
import com.telegram.weatherbot.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

@Component
@AllArgsConstructor
public class GetUserFavoriteList  {

    private final WeatherRepository weatherRepository;


    public List<FavoritesCity> execute(User user){
        return weatherRepository.getListFavoriteCity(user);
    }


}
