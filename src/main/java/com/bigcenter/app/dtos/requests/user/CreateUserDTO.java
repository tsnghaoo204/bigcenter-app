package com.bigcenter.app.dtos.requests.user;


import com.bigcenter.app.status.UserRole;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateUserDTO {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private Boolean enable;
    private UUID tenantId;
    private UserRole role;
}

