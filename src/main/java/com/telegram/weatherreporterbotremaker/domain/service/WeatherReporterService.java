package com.telegram.weatherreporterbotremaker.domain.service;

import com.telegram.weatherbot.data.entity.FavoritesCity;
import com.telegram.weatherbot.data.model.WeatherRequest;
import com.telegram.weatherbot.domain.enums.Status;
import com.telegram.weatherbot.domain.handler.CallbackHandler;
import com.telegram.weatherbot.domain.handler.CommandHandler;
import com.telegram.weatherbot.domain.keyboard.InlineKeyboard;
import com.telegram.weatherbot.domain.model.ResultAction;
import com.telegram.weatherbot.domain.usercase.FindInfoWeatherByCity;
import com.telegram.weatherbot.domain.usercase.GetUserFavoriteList;
import com.telegram.weatherbot.domain.view.WeatherRequestView;
import com.telegram.weatherbot.domain.view.WeatherTomorrowView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherReporterService {

    private final CallbackHandler callbackHandler;

    private final CommandHandler commandHandler;

    private final FindInfoWeatherByCity findInfoWeatherByCity;

    private final GetUserFavoriteList getUserFavoriteList;

    private final InlineKeyboard inlineKeyboard;


    private WeatherRequest weatherRequest;

    private final WeatherRequestView weatherRequestView;

    private final WeatherTomorrowView weatherTomorrowView;

    List<FavoritesCity> cityList = new ArrayList<>();

    private Status status = Status.NO_ACTION;


    public List<SendMessage> getResultMessageCommand(List<SendMessage> messageList,Update currentMessage) throws IOException{
        ResultAction resultAction;
        cityList.clear();
        if (isNeedAction()){
            messageList.add(doAction(new SendMessage(),currentMessage));
        } else {
            resultAction = commandHandler.execute(currentMessage);
            SendMessage message = resultAction.getResult();
            status = resultAction.getStatus();
            messageList.add(message);
            if (resultAction.getResultFavorite().size() != 0){
                messageList.addAll(resultAction.getResultFavorite());
            }
            return messageList;
        }
        return messageList;
    }



    public List<SendMessage> getResultCallbackQuery(List<SendMessage> messageList,Update currentMessage) throws IOException {
        if (weatherRequest != null){
            currentMessage.getCallbackQuery().getMessage().setText(weatherRequest.getWeatherLocation().getName());
        }
        ResultAction resultAction = callbackHandler.execute(currentMessage);
        SendMessage msgs = resultAction.getResult();
        doActionCallback(msgs, resultAction);
        messageList.add(msgs);
        return messageList;
    }




    private boolean isNeedAction(){
        switch (status){

            case ASK_CITY:
            case SEE_FAVORITE_CITY:
                return true;

            case NO_ACTION:
            default:
                return false;
        }
    }

    private SendMessage doAction(SendMessage msgs,Update update) throws  IOException {
        msgs.setChatId(update.getMessage().getChatId());
        User user = update.getMessage().getFrom();
        switch (status){
            case ASK_CITY:
                weatherRequest = findInfoWeatherByCity.execute(update.getMessage().getText());
                msgs.setText(weatherRequestView.toString(weatherRequest));
                inlineKeyboard.setKeyboardMessageWeather(msgs);
                break;

            case SEE_FAVORITE_CITY:
                cityList = getUserFavoriteList.execute(user);
                msgs.setText("Загружаю данные о городах !");
                break;

        }
        status = Status.NO_ACTION;
        return msgs;
    }




    private SendMessage doActionCallback(SendMessage msgs,ResultAction resultAction) throws  IOException{
        String actionCallback = msgs.getText();
        switch (actionCallback){
            case "see-info-weather-tomorrow":
                if (weatherRequest != null) {
                    msgs.setText(weatherTomorrowView.toString(weatherRequest));
                } else {
                    msgs.setText("К сожалению, данные на завтрашнюю погоду были потеряны. " +
                            "Необходимо снова сделать поиск погоды по данному городу.");
                }
                break;

            case "see-info-weather":
                WeatherRequest request = findInfoWeatherByCity.execute(resultAction.getCity());
                msgs.setText(weatherRequestView.toString(request));
                break;

            default:
                return msgs;
        }
        return msgs;
    }


}
