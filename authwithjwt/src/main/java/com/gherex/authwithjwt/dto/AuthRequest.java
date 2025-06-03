package com.gherex.authwithjwt.dto;

import lombok.Getter;
import lombok.Setter;

public class AuthRequest {

    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;

}
