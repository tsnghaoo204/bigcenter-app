package com.bigcenter.app.payloads;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
