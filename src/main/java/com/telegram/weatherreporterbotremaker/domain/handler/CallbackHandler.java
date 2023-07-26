package com.telegram.weatherreporterbotremaker.domain.handler;

import com.telegram.weatherbot.domain.enums.Status;
import com.telegram.weatherbot.domain.model.ResultAction;
import com.telegram.weatherbot.domain.usercase.AddFavoriteCity;
import com.telegram.weatherbot.domain.usercase.RemoveFavoriteCity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class CallbackHandler implements HandlerManager {

    private final ResultAction resultAction;

    private final AddFavoriteCity addFavoriteCity;

    private final RemoveFavoriteCity removeFavoriteCity;

    @Override
    public ResultAction execute(Update update)  {
        SendMessage resultExecute = new SendMessage();
        resultExecute.setChatId(update.getCallbackQuery().getMessage().getChatId());
        String callBackCommand = update.getCallbackQuery().getData();
        User currentUser = update.getCallbackQuery().getFrom();
        resultAction.getResultFavorite().clear();
        String cityDel = thisIsEventDeleteCityFromFavorite(callBackCommand);
        if (cityDel != null){
            callBackCommand = returnNameCallBackRemFavorCity();
        }
        switch (callBackCommand){
            case "ADD_FAVORITE_BUTTON":
                String cityAdd = update.getCallbackQuery().getMessage().getText();
                if (cityAdd.contains("Город")){
                    Pattern pattern = Pattern.compile("Город[Город: а-яA-Я]*");
                    Matcher matcher = pattern.matcher(cityAdd);
                    while(matcher.find())
                        cityAdd = matcher.group().replace("Город:","").trim();
                }
                resultExecute = addFavoriteCity.resultAddCityToFavorite(resultExecute,cityAdd,currentUser);
                break;

            case "BUTTON_REMOVE_FAVORITE_CITY":
                resultExecute = removeFavoriteCity.removeCityFromFavoriteList(resultExecute,cityDel,currentUser);
                break;

            case "BUTTON_TOMORROW_WEATHER_CITY":
                createResultMessage(update,resultExecute,"see-info-weather-tomorrow");
                break;

            case "SEE_WEATHER_INFO":
                resultAction.setCity(update.getCallbackQuery().getMessage().getText());
                createResultMessage(update,resultExecute,"see-info-weather");
                break;

        }
        resultAction.setResult(resultExecute);
        resultAction.setStatus(Status.NO_ACTION);
        return resultAction;
    }

    private String  thisIsEventDeleteCityFromFavorite(String callBackCommand){
        String cityDel = null;
        if (callBackCommand.contains("BUTTON_REMOVE_FAVORITE_CITY")){
            cityDel = callBackCommand.replace("BUTTON_REMOVE_FAVORITE_CITY_","");
        }
        return cityDel;
    }


    private String returnNameCallBackRemFavorCity(){
        return "BUTTON_REMOVE_FAVORITE_CITY";
    }


    private void createResultMessage(Update update, SendMessage resultExecute, String title){
        resultExecute.setChatId(update.getCallbackQuery().getMessage().getChatId());
        resultExecute.setText(title);
    }


}
