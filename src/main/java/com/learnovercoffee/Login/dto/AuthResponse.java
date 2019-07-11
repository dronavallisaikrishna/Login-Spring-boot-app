package com.learnovercoffee.Login.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
