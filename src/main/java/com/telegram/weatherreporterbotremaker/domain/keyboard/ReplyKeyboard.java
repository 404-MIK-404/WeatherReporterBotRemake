package com.telegram.weatherreporterbotremaker.domain.keyboard;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ReplyKeyboard {


    private final List<KeyboardRow> keyboardRowList = new ArrayList<>();

    private final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    private final List<String> listRow = Arrays.asList("Узнать погоду \uD83D\uDDD3",
            "Посмотреть избранное ✅");

    {
        KeyboardRow keyboardButtons;
        for (String s : listRow) {
            keyboardButtons = new KeyboardRow();
            keyboardButtons.add(s);
            keyboardRowList.add(keyboardButtons);
        }
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
        replyKeyboardMarkup.setResizeKeyboard(true);
    }


    public void setKeyboardBot(SendMessage sendMessage){
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }



}