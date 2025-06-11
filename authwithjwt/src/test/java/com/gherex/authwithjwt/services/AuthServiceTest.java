package com.gherex.authwithjwt.services;

import com.gherex.authwithjwt.dto.AuthRequest;
import com.gherex.authwithjwt.dto.AuthResponse;
import com.gherex.authwithjwt.dto.RegisterRequest;
import com.gherex.authwithjwt.entities.User;
import com.gherex.authwithjwt.repositories.UserRepository;
import com.gherex.authwithjwt.repositories.AuthorityRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private AuthenticationManager authManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthorityRepository authorityRepository;

    @InjectMocks
    private AuthService authService; // Va a inyectar los mocks

    @Test
    void login_deberiaRetornarTokenSiAuthExitosa() {
        AuthRequest request = new AuthRequest("admin", "1234");

        Authentication authentication = mock(Authentication.class);
        UserDetails userDetails = mock(UserDetails.class);

        when(authManager.authenticate(any())).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("fake-jwt-token");

        AuthResponse response = authService.login(request);

        assertEquals("fake-jwt-token", response.getToken());
    }

    @Test
    void register_deberiaGuardarUsuarioYAsignarRoles() {
        RegisterRequest request = new RegisterRequest("nuevoUsuario", "1234");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        authService.register(request);

        verify(userRepository).save(any(User.class));
        verify(authorityRepository).save(any());
    }

}
