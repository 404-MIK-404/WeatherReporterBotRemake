package com.telegram.weatherreporterbotremaker.app.controller;

import com.telegram.weatherbot.app.config.ConfigTelegram;
import com.telegram.weatherbot.domain.enums.Commands;
import com.telegram.weatherbot.domain.service.WeatherReporterService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Component
public class WeatherReporterBot extends TelegramLongPollingBot {

    private final ConfigTelegram configTelegram;

    private final WeatherReporterService weatherReporterService;


    private List<SendMessage> result;


    @PostConstruct
    private void init() throws TelegramApiException {
        this.execute(new SetMyCommands(Commands.getCommandList(),new BotCommandScopeDefault(),null));
    }

    @Override
    public String getBotToken() {
        return configTelegram.getApi();
    }

    @Override
    public String getBotUsername() {
        return configTelegram.getName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        result.clear();
        try {
            if (update.hasMessage()){
                result = weatherReporterService.getResultMessageCommand(result,update);
                sendMessage();
            } else if (update.hasCallbackQuery()){
                result = weatherReporterService.getResultCallbackQuery(result,update);
                sendMessage();
            }
        } catch (RuntimeException | TelegramApiException | IOException ex){
            System.out.println(ex.fillInStackTrace());
        }
    }

    private void sendMessage() throws TelegramApiException {
        for (SendMessage msgs : result) {
            this.execute(msgs);
        }
    }


}
