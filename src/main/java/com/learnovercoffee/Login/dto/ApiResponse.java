package com.learnovercoffee.Login.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiResponse {
    private boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
