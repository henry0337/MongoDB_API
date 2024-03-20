package com.henry.newmongodbapi.services;

import com.henry.newmongodbapi.dto.SignInRequest;
import com.henry.newmongodbapi.dto.SignUpRequest;
import com.henry.newmongodbapi.dto.AuthResponse;
import com.henry.newmongodbapi.dto.RefreshTokenRequest;
import com.henry.newmongodbapi.models.User;
import com.henry.newmongodbapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.henry.newmongodbapi.models.Role.USER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager manager;
    private final JwtService jwtService;

    public void register(SignUpRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setStatus(request.getStatus());
        user.setRole(USER);
        user.setIsAccountExpired(request.getIsAccountExpired());
        user.setIsAccountLocked((request.getIsAccountLocked()));
        user.setIsCredentialsExpired(request.getIsCredentialsExpired());

        userRepository.save(user);
    }

    public AuthResponse login(SignInRequest request) {
        manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("Invalid email or password provided!")
        );
        String jwt = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        response.setRefreshToken(refreshToken);

        return response;
    }

    public AuthResponse renewToken(RefreshTokenRequest request) {
        String username = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByUsername(username).orElseThrow();

        if (jwtService.isTokenValid(request.getToken(), user)) {
            String jwt = jwtService.generateToken(user);

            AuthResponse response = new AuthResponse();
            response.setToken(jwt);
            response.setRefreshToken(request.getToken());

            return response;
        }
        return null;
    }
}
