package com.bigcenter.app.dtos.requests.room;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateRoomDTO {
    private String name;
    private Integer capacity;
    private UUID tenantId;
}
