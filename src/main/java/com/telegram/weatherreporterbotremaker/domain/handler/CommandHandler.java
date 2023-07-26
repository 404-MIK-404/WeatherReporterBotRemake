package com.telegram.weatherreporterbotremaker.domain.handler;


import com.telegram.weatherbot.data.entity.FavoritesCity;
import com.telegram.weatherbot.domain.enums.Commands;
import com.telegram.weatherbot.domain.enums.Status;
import com.telegram.weatherbot.domain.keyboard.InlineKeyboard;
import com.telegram.weatherbot.domain.keyboard.ReplyKeyboard;
import com.telegram.weatherbot.domain.model.ResultAction;
import com.telegram.weatherbot.domain.usercase.GetUserFavoriteList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CommandHandler implements HandlerManager {

    private final List<BotCommand> listCommand = Commands.getCommandList();

    private final ResultAction resultAction;

    private final InlineKeyboard inlineKeyboard;

    private final GetUserFavoriteList getUserFavoriteList;

    private final ReplyKeyboard replyKeyboard;

    List<FavoritesCity> cityList = new ArrayList<>();


    @Override
    public ResultAction execute(Update update) {
        StringBuilder command = new StringBuilder(update.getMessage().getText());
        SendMessage resultMS = new SendMessage();
        resultAction.getResultFavorite().clear();
        if (containsCommand(command)){
            isTextCommand(command,resultMS,update);
        } else {
            isButtonCommand(command,resultMS,update);
        }
        resultAction.setResult(resultMS);
        return resultAction;
    }


    public boolean containsCommand(final StringBuilder name){
        return listCommand.stream().anyMatch(o -> o.getCommand().contentEquals(name));
    }

    private void isButtonCommand(StringBuilder command,SendMessage message,Update update){
        cityList.clear();
        switch (command.toString()){

            case "Узнать погоду \uD83D\uDDD3":
                resultAction.setStatus(Status.ASK_CITY);
                createResultMessage(update,message,"Напишите город");
                break;

            case "Посмотреть избранное ✅":
                resultAction.setStatus(Status.NO_ACTION);
                cityList = getUserFavoriteList.execute(update.getMessage().getFrom());
                setFavoriteListToMessage(message,update);
                break;

            default:
                resultAction.setStatus(Status.NO_ACTION);
                createResultMessage(update,message,"Я не понимаю твою команду мальчик !");
                break;

        }
    }

    private void isTextCommand(StringBuilder command, SendMessage result ,Update update){

        switch (command.toString()) {

            case "/start":
                replyKeyboard.setKeyboardBot(result);
                resultAction.setStatus(Status.NO_ACTION);
                createResultMessage(update,result,"Привет мир");
                break;

            case "/help":
                replyKeyboard.setKeyboardBot(result);
                resultAction.setStatus(Status.NO_ACTION);
                createResultMessage(update,result,"Это help комманда");
                break;

            case "/weather":
                resultAction.setStatus(Status.ASK_CITY);
                createResultMessage(update,result,"Напишите город");
                break;
        }
    }

    private void setFavoriteListToMessage(SendMessage message,Update update){
        if (cityList.size() != 0){
            for (FavoritesCity city : cityList){
                SendMessage messageCity = new SendMessage();
                messageCity.setChatId(update.getMessage().getChatId());
                messageCity.setText(city.getCity());
                inlineKeyboard.setKeyboardMessageDelFavorite(messageCity);
                resultAction.getResultFavorite().add(messageCity);
            }
            createResultMessage(update,message,"Отображаю избранные города");
        } else {
            createResultMessage(update,message,"Список избранного пуст.\nЧтобы добавить город в избранного необходимо," +
                    " найти информацию о городе и воспользоваться кнопкой 'Добавить в избранное'");
        }
    }

    private void createResultMessage(Update update,SendMessage resultExecute,String text){
        resultExecute.setChatId(update.getMessage().getChatId());
        resultExecute.setText(text);
    }


}
