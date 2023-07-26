package com.telegram.weatherreporterbotremaker.app.dto;


import com.telegram.weatherbot.data.entity.FavoritesCity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class UserDTO {

    private String login;

    private List<FavoritesCity> cityList;

}
