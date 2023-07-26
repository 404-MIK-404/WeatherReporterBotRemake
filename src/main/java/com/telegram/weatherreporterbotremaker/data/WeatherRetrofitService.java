package com.telegram.weatherreporterbotremaker.data;

import com.telegram.weatherbot.data.entity.FavoritesCity;
import com.telegram.weatherbot.data.model.WeatherRequest;
import com.telegram.weatherbot.data.repository.UserRepository;
import com.telegram.weatherbot.domain.repository.WeatherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class WeatherRetrofitService implements WeatherRepository {


    private final RetrofitWeatherInstance retrofitWeatherInstance;

    private final UserRepository userRepository;

    @Override
    public WeatherRequest findInfoWeatherByCity(String city) throws IOException {
        return retrofitWeatherInstance.findWeatherByCity(city);
    }

    @Override
    public List<FavoritesCity> getListFavoriteCity(User user) {
        return userRepository.findByLogin(user.getUserName()).getFavoritesCity();
    }

    @Override
    public SendMessage saveCityToFavorite(SendMessage message,String city, User user) {
        try {
            com.telegram.weatherbot.data.entity.User userRes = userRepository.findByLoginAndTelegramId(user.getUserName(), user.getId());
            if (userRes == null){
                userRes = createUser(user);
            }
            boolean resSearch = userRes.getFavoritesCity().stream().anyMatch(val->val.getCity().equals(city));
            if (!resSearch){
                FavoritesCity cityFavor = new FavoritesCity();
                cityFavor.setCity(city);
                userRes.getFavoritesCity().add(cityFavor);
                userRepository.save(userRes);
                message.setText("Город " + city+  " добавлен в избранное");
            } else {
                message.setText("Город " + city + " уже есть в избранном");
            }
            return message;
        } catch (RuntimeException ex){
            message.setText("Произошла ошибка: " + ex.getMessage());
            return message;
        }
    }

    @Override
    public SendMessage removeCityFromFavorite(SendMessage message,String city, User user) {
        try {
            com.telegram.weatherbot.data.entity.User userRes = userRepository.findByLoginAndTelegramId(user.getUserName(), user.getId());
            if (userRes.getFavoritesCity().size() != 0){
                int indexFind = findIndexByFavoriteCity(userRes.getFavoritesCity(),city);
                if (indexFind != -1 ) {
                    userRes.getFavoritesCity().remove(indexFind);
                    userRepository.save(userRes);
                    message.setText("Город "+ city + " успешно удалён из избранного");
                } else {
                    message.setText("Город " + city + " не удалён из избранного так данный отсуствует в избранном.");
                }
            } else {
                message.setText("Список избранного пуст, отмена операций");
            }
            return message;
        } catch (RuntimeException ex){
            message.setText("Произошла ошибка: " + ex.getMessage());
            return message;
        }
    }


    private com.telegram.weatherbot.data.entity.User createUser(User user){
        com.telegram.weatherbot.data.entity.User authUser = new com.telegram.weatherbot.data.entity.User();
        authUser.setTelegramId(user.getId());
        authUser.setLogin(user.getUserName());
        userRepository.save(authUser);
        return authUser;
    }

    private int findIndexByFavoriteCity(List<FavoritesCity> favoritesCity,String cityField){
        return   IntStream.range(0, favoritesCity.size())
                .filter(i -> favoritesCity.get(i).getCity().equals(cityField))
                .findFirst().orElse(-1);
    }

}
