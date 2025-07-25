package com.bigcenter.app.dtos.requests.room;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateRoomDTO {
    private UUID id;
    private String name;
    private Integer capacity;
}
