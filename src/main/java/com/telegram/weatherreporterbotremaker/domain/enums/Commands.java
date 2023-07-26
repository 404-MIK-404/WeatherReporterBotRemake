package com.telegram.weatherreporterbotremaker.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
@AllArgsConstructor
public class Commands {
    private static final List<BotCommand> commandList = new ArrayList<>();

    static {
        List<CommandsDetails> commandsDetailsList = CommandsDetails.getCommandsDetailsList();
        for (CommandsDetails details : commandsDetailsList) {
            commandList.add(new BotCommand(details.getCommand(), details.getDescription()));
        }

    }

    public static List<BotCommand> getCommandList(){
        return commandList;
    }


}
