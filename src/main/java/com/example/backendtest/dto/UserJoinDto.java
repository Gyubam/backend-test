package com.example.backendtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserJoinDto {

    private String email;

    private String password;

    private String username;

    private String tel;

    private List<String> roles = new ArrayList<>();
}
