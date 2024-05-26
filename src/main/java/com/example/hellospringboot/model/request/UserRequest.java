package com.example.hellospringboot.model.request;

import lombok.Data;

@Data
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private String role;
    private String token;
    private String refreshToken;
}
