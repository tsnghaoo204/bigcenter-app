package com.bigcenter.app.dtos.requests.tenant;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTenantDTO {
    private UUID id;
    private String name;
    private String logoUrl;
}
