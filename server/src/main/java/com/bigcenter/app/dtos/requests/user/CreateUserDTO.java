package com.bigcenter.app.dtos.requests.user;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Boolean enable;
}

