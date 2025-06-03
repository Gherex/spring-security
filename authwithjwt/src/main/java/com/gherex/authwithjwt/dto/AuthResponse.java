package com.gherex.authwithjwt.dto;

import lombok.Getter;

public class AuthResponse {

    @Getter
    private final String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}
