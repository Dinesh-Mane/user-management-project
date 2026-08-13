package com.dineshmane.project.mapper;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;

public class UserMapper {

    // map User JPA entity into UserDto
    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDto;
    }

    // map UserDto into User JPA entity
    public static User mapToUser(UserDto userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }


}
