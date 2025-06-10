package com.gherex.authwithjwt.dto;

import lombok.Getter;

public record RegisterRequest(@Getter String username, @Getter String password) {
}
