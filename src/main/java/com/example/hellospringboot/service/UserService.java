package com.example.hellospringboot.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.hellospringboot.entity.User;
import com.example.hellospringboot.model.dto.UserDto;
import com.example.hellospringboot.model.response.ResponseApi;
import com.example.hellospringboot.repository.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseApi<?>> createUser(UserDto userDto) {
        userRepository.save(modelMapper.map(userDto, User.class));
        return ResponseEntity.ok(new ResponseApi<>(200, "Success", userDto));
    }

    public ResponseEntity<ResponseApi<?>> getAllUsers() {
        return ResponseEntity.ok(new ResponseApi<>(200, "Success", userRepository.findAll()));
    }

    public ResponseEntity<ResponseApi<?>> getUserById(Long id) {
        return ResponseEntity.ok(new ResponseApi<>(200, "Success", userRepository.findById(id).get()));
    }

    public ResponseEntity<ResponseApi<?>> getUserByEmail(String email) {
        return ResponseEntity.ok(new ResponseApi<>(200, "Success", userRepository.findByEmail(email)));
    }

    public ResponseEntity<ResponseApi<?>> updateUserById(Long id, UserDto userDto) {
        User user = userRepository.findById(id).get();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return ResponseEntity.ok(new ResponseApi<>(200, "Success", user));
    }

    public ResponseEntity<ResponseApi<?>> deleteUserById(Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok(new ResponseApi<>(200, "Success"));
    }

}
