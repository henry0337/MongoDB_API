package com.henry.newmongodbapi.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String username;
    private String password;
    private Short status;
}
