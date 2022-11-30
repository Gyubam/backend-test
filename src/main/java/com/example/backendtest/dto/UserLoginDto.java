package com.example.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginDto {

    private String email;

    private String password;
}
