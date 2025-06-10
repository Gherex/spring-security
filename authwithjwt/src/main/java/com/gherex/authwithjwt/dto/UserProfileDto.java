package com.gherex.authwithjwt.dto;

import lombok.Getter;

import java.util.List;

public record UserProfileDto(@Getter String username, List<String> roles) {
}
