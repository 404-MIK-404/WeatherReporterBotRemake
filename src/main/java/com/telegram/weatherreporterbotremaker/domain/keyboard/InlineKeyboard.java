package com.telegram.weatherreporterbotremaker.domain.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InlineKeyboard {

    private InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();

    private List<List<List<InlineKeyboardButton>>> rowsInLine = new ArrayList<>();

    private List<List<String>> nameCallback = Arrays.asList(
            Arrays.asList("BUTTON_REMOVE_FAVORITE_CITY","SEE_WEATHER_INFO"),
            Arrays.asList("ADD_FAVORITE_BUTTON","BUTTON_TOMORROW_WEATHER_CITY"));

    private List<List<String>> titleButton = Arrays.asList(
            Arrays.asList("Удалить из избранного ❌","Узнать погоду поподробнее"),
            Arrays.asList("Добавить в избранное ✅","Узнать погоду на завтра \uD83D\uDDD3"));


    {
        for (int i = 0; i < titleButton.size();i++){
            rowsInLine.add(new ArrayList<>());
            for (int j = 0; j < nameCallback.get(i).size();j++){
                rowsInLine.get(i).add(new ArrayList<>());
                InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(titleButton.get(i).get(j));
                inlineKeyboardButton.setCallbackData(nameCallback.get(i).get(j));
                rowsInLine.get(rowsInLine.size() - 1)
                        .get(rowsInLine.get(i).size() - 1).add(inlineKeyboardButton);
            }
        }

    }


    public void setKeyboardMessageDelFavorite(SendMessage message){
        List<List<InlineKeyboardButton>> rowsDel = new ArrayList<>();
        InlineKeyboardMarkup markupInLine = new InlineKeyboardMarkup();
        for (int i = 0; i < rowsInLine.get(0).size(); i++){
            rowsDel.add(new ArrayList<>());
            String callback = rowsInLine.get(0).get(i).get(0).getCallbackData();
            String title = rowsInLine.get(0).get(i).get(0).getText();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText(title);
            if (callback.contains("BUTTON_REMOVE_FAVORITE_CITY")){
                inlineKeyboardButton.setCallbackData(callback + "_" + message.getText());
            } else {
                inlineKeyboardButton.setCallbackData(callback);
            }
            rowsDel.get(rowsDel.size() - 1).add(inlineKeyboardButton);
        }
        markupInLine.setKeyboard(rowsDel);
        message.setReplyMarkup(markupInLine);
    }

    public void setKeyboardMessageWeather(SendMessage message){
        markupInLine.setKeyboard(rowsInLine.get(1));
        message.setReplyMarkup(markupInLine);
    }


}
