package com.example.hellospringboot.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hellospringboot.entity.User;
import com.example.hellospringboot.model.dto.UserDto;
import com.example.hellospringboot.model.request.UserRequest;
import com.example.hellospringboot.model.response.ResponseApi;
import com.example.hellospringboot.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
    private static final String SUCCESS_MESSAGE = "Success";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseApi<?>> createUser(UserDto userDto) {
        try {
            User user = modelMapper.map(userDto, User.class);
            userRepository.save(user);
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.CREATED.value(), "User created successfully", user),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error creating user"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseApi<?>> getAllUsers() {
        try {
            return ResponseEntity
                    .ok(new ResponseApi<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, userRepository.findAll()));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error retrieving users"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseApi<?>> getUserById(Long id) {
        log.info("getUserById", id);
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                return ResponseEntity.ok(new ResponseApi<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, userOptional.get()));
            } else {
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.NOT_FOUND.value(), "User not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error retrieving user"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseApi<?>> getUserByEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                return ResponseEntity.ok(new ResponseApi<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, user));
            } else {
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.NOT_FOUND.value(), "User not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error retrieving user"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseApi<?>> updateUserById(Long id, UserRequest userRequest) {
        log.info("Updating user with id: {}", id, userRequest);
        try {
            Optional<User> userOptional = userRepository.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                modelMapper.map(userRequest, user);
                userRepository.save(user);
                return ResponseEntity.ok(new ResponseApi<>(HttpStatus.OK.value(), "User updated successfully", user));
            } else {
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.NOT_FOUND.value(), "User not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error updating user"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseApi<?>> deleteUserById(Long id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return ResponseEntity.ok(new ResponseApi<>(HttpStatus.OK.value(), "User deleted successfully"));
            } else {
                return new ResponseEntity<>(new ResponseApi<>(HttpStatus.NOT_FOUND.value(), "User not found"),
                        HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ResponseApi<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error deleting user"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
