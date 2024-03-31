package com.example.hellospringboot.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;
    private String token;
    private String refreshToken;
}
