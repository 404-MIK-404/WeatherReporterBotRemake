package com.telegram.weatherreporterbotremaker.domain.model;


import com.telegram.weatherbot.domain.enums.Status;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class ResultAction {

    SendMessage result;

    Status status;

    List<SendMessage> resultFavorite = new ArrayList<>();

    String city;

}
