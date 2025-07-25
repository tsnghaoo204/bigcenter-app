package com.bigcenter.app.dtos.responses;

import com.bigcenter.app.status.UserRole;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String username;
    private String fullName;
    private String email;
    private Boolean enable;
    private Instant createAt;
    private UUID tenantId;
    private String tenantName;
    private UserRole role;
}

