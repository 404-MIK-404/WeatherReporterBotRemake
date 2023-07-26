package com.telegram.weatherreporterbotremaker.domain.view;

import com.telegram.weatherbot.data.model.WeatherForecastDay;
import com.telegram.weatherbot.data.model.WeatherForecastTomorrow;
import com.telegram.weatherbot.data.model.WeatherLocation;
import com.telegram.weatherbot.data.model.WeatherRequest;
import org.springframework.stereotype.Component;


@Component
public class WeatherTomorrowView {


    public String toString(WeatherRequest weatherRequest){
        WeatherLocation location = weatherRequest.getWeatherLocation();
        WeatherForecastDay forecastDay = weatherRequest.getWeatherForecast().getWeatherForecastDay().get(1);
        WeatherForecastTomorrow tomorrow = forecastDay.getWeatherForecastTomorrow();
        return String.format("Погода на завтра \uD83D\uDDD3 \n" +
                "Город: \t %s \n" +
                "--------------------------------------------------- \n" +
                "\uD83D\uDD25 Максимальная температура: \t %s °C\n" +
                "❄ Минимальная температура: \t %s °C\n" +
                "\uD83C\uDF21 Средняя температура: \t %s °C\n" +
                "\uD83C\uDF2C Максимальная скорость ветра: \t %s миль/ч\n" +
                "☀ Сила ультрафиолетового (УФ) излучения солнца: \t %s \n" +
                "\uD83D\uDCA7 Средняя относительная влажность: \t %s %%\n",
                location.getName(),
                tomorrow.getMaxTempC(),
                tomorrow.getMinTempC(),
                tomorrow.getAvgTempC(),
                tomorrow.getMaxWindKph(),
                tomorrow.getUv(),
                tomorrow.getAvgHumidity());
    }

}
