package com.gherex.authwithjwt.controllers;

import com.gherex.authwithjwt.dto.AuthRequest;
import com.gherex.authwithjwt.dto.AuthResponse;
import com.gherex.authwithjwt.dto.RegisterRequest;
import com.gherex.authwithjwt.dto.UserProfileDto;
import com.gherex.authwithjwt.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        System.out.println("Entro al controlador de registro");
        authService.register(request);
        System.out.println("Salio de authService.register");
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> getProfile() {
        UserProfileDto userProfileDto = authService.getProfile();
        return ResponseEntity.ok(userProfileDto);
    }

}



