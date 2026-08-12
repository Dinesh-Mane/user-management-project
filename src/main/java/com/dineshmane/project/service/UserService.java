package com.dineshmane.project.service;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto);

    void deleteUserById(Long userId);

}
