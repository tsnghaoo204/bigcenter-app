package com.bigcenter.app.dtos.requests.user;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdateUserDTO {
    private UUID id;
    private String fullName;
    private String email;
    private Boolean enable;
}

