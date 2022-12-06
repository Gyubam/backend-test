package com.example.backendtest.dto;

import lombok.Data;

import java.util.Map;

@Data
public class RefreshApiResponseMessage {

    private String message;
    private String status;

    public RefreshApiResponseMessage(Map<String, String> map) {
        this.message = map.get("message");
        this.status = map.get("status");
    }
}
