package com.dineshmane.project.service.impl;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;
import com.dineshmane.project.mapper.UserMapper;
import com.dineshmane.project.repository.UserRepository;
import com.dineshmane.project.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {

        // convert UserDto into User JPA entity
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto, User.class);

        User userSaved =  userRepository.save(user);

        // convert User JPA entity into UserDto
//        UserDto savedUserDto = UserMapper.mapToUserDto(userSaved);
        UserDto savedUserDto = modelMapper.map(userSaved, UserDto.class);
        return savedUserDto;

    }

    @Override
    public UserDto getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();

        // convert User JPA entity into UserDto
//        UserDto userDto = UserMapper.mapToUserDto(user);
        UserDto userDto = modelMapper.map(user, UserDto.class);
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

//        return users.stream().map(UserMapper::mapToUserDto)
//                .collect(Collectors.toList());
        return users.stream().map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        // convert UserDto into User JPA entity
//        User user = UserMapper.mapToUser(userDto);
        User user = modelMapper.map(userDto, User.class);

        User existingUser = userRepository.findById(user.getId()).get();

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = userRepository.save(existingUser);

        // convert User JPA entity into UserDto
//        UserDto updatedUserDto = UserMapper.mapToUserDto(updatedUser);
        UserDto updatedUserDto = modelMapper.map(updatedUser, UserDto.class);
        return updatedUserDto;
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
