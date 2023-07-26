package com.telegram.weatherreporterbotremaker.app.dto;

import com.telegram.weatherbot.data.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO convertToDTO(User user);

}
