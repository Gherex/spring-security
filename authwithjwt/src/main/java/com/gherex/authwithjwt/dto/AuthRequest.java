package com.gherex.authwithjwt.dto;

import lombok.Getter;

public record AuthRequest(@Getter String username, @Getter String password) {
}
