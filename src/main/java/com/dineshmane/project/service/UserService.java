package com.dineshmane.project.service;

import com.dineshmane.project.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User user);

    User getUserById(Long userId);

    List<User> getAllUsers();

    User updateUser(User user);

    void deleteUserById(Long userId);

}
