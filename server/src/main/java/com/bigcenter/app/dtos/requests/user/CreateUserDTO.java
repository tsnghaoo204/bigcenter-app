package com.bigcenter.app.dtos.requests.user;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDTO {
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private Boolean enable;
}

