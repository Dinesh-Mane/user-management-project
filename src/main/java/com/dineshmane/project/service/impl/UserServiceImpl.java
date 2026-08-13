package com.dineshmane.project.service.impl;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;
import com.dineshmane.project.mapper.UserMapper;
import com.dineshmane.project.repository.UserRepository;
import com.dineshmane.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        // convert UserDto into User JPA entity
        User user = UserMapper.mapToUser(userDto);
        User userSaved =  userRepository.save(user);

        // convert User JPA entity into UserDto
        UserDto savedUserDto = UserMapper.mapToUserDto(userSaved);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        // convert User JPA entity into UserDto
        UserDto userDto = UserMapper.mapToUserDto(user);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        // convert User JPA entity list into UserDto list
//        List<UserDto> usersDto = new ArrayList<>();
//        for (User user:users){
//            usersDto.add(UserMapper.mapToUserDto(user));
//        }
//        return usersDto;

        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        // convert UserDto into User JPA entity
        User user = UserMapper.mapToUser(userDto);

        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        // convert User JPA entity into UserDto
        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
        return updatedUserDto;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
