package com.example.hellospringboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hellospringboot.model.dto.UserDto;
import com.example.hellospringboot.model.request.UserRequest;
import com.example.hellospringboot.model.response.ResponseApi;
import com.example.hellospringboot.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ResponseApi<?>> createUser(@RequestBody UserDto user) {
        return userService.createUser(user);
    }

    @GetMapping
    public ResponseEntity<ResponseApi<?>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> updateUserById(@PathVariable("id") Long id, @RequestBody UserRequest user) {
        return userService.updateUserById(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseApi<?>> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }

}
