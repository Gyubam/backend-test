package com.example.backendtest.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RefreshApiResponseMessage {

    private String message;
    private String status;
    private String accessToken;

    public RefreshApiResponseMessage(Map<String, String> map) {
        this.message = map.get("message");
        this.status = map.get("status");
        this.accessToken = map.get("accessToken");
    }
}
