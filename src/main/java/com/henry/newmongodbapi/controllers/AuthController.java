package com.henry.newmongodbapi.controllers;

import com.henry.newmongodbapi.dto.AuthResponse;
import com.henry.newmongodbapi.dto.RefreshTokenRequest;
import com.henry.newmongodbapi.dto.SignInRequest;
import com.henry.newmongodbapi.dto.SignUpRequest;
import com.henry.newmongodbapi.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request) {
        authService.register(request);
        return ResponseEntity.ok("Đã đăng ký thành công!");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.renewToken(request));
    }
}
