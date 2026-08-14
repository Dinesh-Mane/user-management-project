package com.dineshmane.project.controller;

import com.dineshmane.project.dto.UserDto;
import com.dineshmane.project.entity.User;
import com.dineshmane.project.exception.ErrorDetails;
import com.dineshmane.project.exception.ResourceNotFoundException;
import com.dineshmane.project.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    // build create user REST API
    // http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }


    // build get user by id REST API
    // http://localhost:8080/api/users/3
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto userDto = userService.getUserById(userId);
        return ResponseEntity.ok(userDto);
    }

    // build get all users REST API
    // http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        List<UserDto> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    // build update user REST API
    // http://localhost:8080/api/users/3
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId, @RequestBody UserDto userDto) {
        userDto.setId(userId);
        UserDto UpdatedUserDto = userService.updateUser(userDto);
        return new ResponseEntity<>(UpdatedUserDto, HttpStatus.CREATED);
    }

    // build delete user by id REST API
    // http://localhost:8080/api/users/3
    @DeleteMapping("{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User Deleted!", HttpStatus.CREATED);
    }

    // to handle specific exception
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(false),
//                "USER_NOT_FOUND"
//        );
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//
//    }
}
