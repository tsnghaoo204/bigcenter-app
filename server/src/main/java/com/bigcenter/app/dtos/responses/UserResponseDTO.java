package com.bigcenter.app.dtos.responses;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private UUID id;
    private String fullName;
    private String email;
    private String phone;
    private Boolean enable;
    private Instant createAt;
}

