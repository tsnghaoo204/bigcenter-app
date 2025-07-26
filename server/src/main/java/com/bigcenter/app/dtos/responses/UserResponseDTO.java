package com.bigcenter.app.dtos.responses;

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
}

