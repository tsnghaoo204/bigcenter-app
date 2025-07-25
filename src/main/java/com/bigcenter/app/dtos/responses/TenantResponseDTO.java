package com.bigcenter.app.dtos.responses;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class TenantResponseDTO {
    private UUID id;
    private String name;
    private String code;
    private String logoUrl;
    private Instant createAt;
}

