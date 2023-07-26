package com.telegram.weatherreporterbotremaker.data.repository;

import com.telegram.weatherbot.data.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    @Query(value = "{ login: ?0,  telegramId:  ?1 }")
    User findByLoginAndTelegramId(String login,Long telegramId);

    @Query(value = "{login:  ?0}")
    User findByLogin(String login);

}
