package com.example.hellospringboot.model.response;

import lombok.Data;

@Data
public class ResponseApi<T> {
    private Integer status;
    private String message;
    private T data;

    public ResponseApi(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseApi(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
