package com.gherex.authwithjwt.services;

import com.gherex.authwithjwt.dto.AuthRequest;
import com.gherex.authwithjwt.dto.AuthResponse;
import com.gherex.authwithjwt.dto.RegisterRequest;
import com.gherex.authwithjwt.dto.UserProfileDto;
import com.gherex.authwithjwt.entities.Authority;
import com.gherex.authwithjwt.repositories.AuthorityRepository;
import com.gherex.authwithjwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.gherex.authwithjwt.entities.User;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Ya existe un usuario con ese username");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);

        userRepository.save(user);
        authorityRepository.save(new Authority(user.getUsername(), "ROLE_USER"));
    }

    public UserProfileDto getProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado")));

        List<String> roles = user.get().getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return new UserProfileDto(user.get().getUsername(), roles);
    }
}


