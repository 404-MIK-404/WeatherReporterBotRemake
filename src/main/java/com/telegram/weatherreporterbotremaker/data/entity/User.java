package com.telegram.weatherreporterbotremaker.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field(name = "login")
    private String login;

    @Field(name = "telegramId")
    private Long telegramId;

    @Field(name = "city")
    private List<FavoritesCity> favoritesCity = new ArrayList<>();

}
