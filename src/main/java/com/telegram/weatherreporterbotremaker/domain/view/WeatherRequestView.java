package com.telegram.weatherreporterbotremaker.domain.view;


import com.telegram.weatherbot.data.model.WeatherLocation;
import com.telegram.weatherbot.data.model.WeatherMainInfo;
import com.telegram.weatherbot.data.model.WeatherRequest;
import org.springframework.stereotype.Component;


@Component
public class WeatherRequestView {

    public String toString(WeatherRequest weatherRequest){
        WeatherLocation location = weatherRequest.getWeatherLocation();
        WeatherMainInfo mainInfo = weatherRequest.getWeatherMainInfo();
        return String.format("Погода на сегодня.\uD83D\uDDD3 \n" +
                        "Город: \t %s \n" +
                        "Страна: \t %s \n" +
                        "--------------------------------------------------- \n" +
                        "\uD83C\uDF21 Текущая температура: \t %s °C\n" +
                        "\uD83C\uDF2C Скорость ветра: \t %s км/ч\n" +
                        "\uD83D\uDCA7 Влажность: \t %s %%\n" +
                        "\uD83E\uDD12 Температура по ощущению: \t %s °C\n" +
                        "❤ \u200D\uD83E\uDE79 Давление: \t %s мбар.\n" +
                        "☀ Сила ультрафиолетового (УФ) излучения солнца: \t %s \n" +
                        "\uD83D\uDCA8 Порывы ветра: \t %s км/ч\n",
                location.getName(),
                location.getCountry(),
                mainInfo.getTempC(),
                mainInfo.getWindKph(),
                mainInfo.getHumidity(),
                mainInfo.getFeelsLikeC(),
                mainInfo.getPressureMb(),
                mainInfo.getUv(),
                mainInfo.getGustKph());
    }


}
