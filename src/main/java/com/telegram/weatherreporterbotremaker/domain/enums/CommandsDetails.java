package com.telegram.weatherreporterbotremaker.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@Getter
public class CommandsDetails {

    private String command;
    private String description;

    private static final List<CommandsDetails> commandsDetailsList = Arrays.asList(
            new CommandsDetails("/help","Возвращает список команд и описание этих команд"),
            new CommandsDetails("/start","Запускает и возвращает сообщение о начале работе бота"),
            new CommandsDetails("/weather","Отображает данные о погоде в выбранном городе")
    );

    public static List<CommandsDetails> getCommandsDetailsList(){
        return commandsDetailsList;
    }

}
