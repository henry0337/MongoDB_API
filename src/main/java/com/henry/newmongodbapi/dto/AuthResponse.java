package com.henry.newmongodbapi.dto;

import lombok.Data;

@Data
public class AuthResponse {
    private String token;
    private String refreshToken;
}
