package com.bigcenter.app.dtos.requests.tenant;

import lombok.Data;

@Data
public class CreateTenantDTO {
    private String name;
    private String code;
    private String logoUrl;
}

