package com.bigcenter.app.payloads.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
