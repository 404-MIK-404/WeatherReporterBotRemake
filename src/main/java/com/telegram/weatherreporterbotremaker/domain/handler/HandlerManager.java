package com.telegram.weatherreporterbotremaker.domain.handler;

import com.telegram.weatherbot.domain.model.ResultAction;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface HandlerManager {

    ResultAction execute(Update update);

}
