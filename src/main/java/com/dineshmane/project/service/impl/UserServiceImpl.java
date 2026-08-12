package com.dineshmane.project.service.impl;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;
import com.dineshmane.project.repository.UserRepository;
import com.dineshmane.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // convert UserDto into User JPA entity
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        User userSaved =  userRepository.save(user);

        // convert User JPA entity into UserDto
        UserDto savedUserDto = new UserDto(
                userSaved.getId(),
                userSaved.getFirstName(),
                userSaved.getLastName(),
                userSaved.getEmail()
        );
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        // convert User JPA entity into UserDto
        UserDto savedUserDto = new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return savedUserDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // convert User JPA entity list into UserDto list
        List<UserDto> usersDto = new ArrayList<>();
        for (User user:users){
            usersDto.add(new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail()));
        }
        return usersDto;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        // convert UserDto into User JPA entity
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );

        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        // convert User JPA entity into UserDto
        UserDto updatedUserDto = new UserDto(
                updatedUser.getId(),
                updatedUser.getFirstName(),
                updatedUser.getLastName(),
                updatedUser.getEmail()
        );
        return updatedUserDto;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
