package com.bigcenter.app.dtos.responses;

import lombok.Data;

import java.util.UUID;

@Data
public class RoomResponseDTO {
    private UUID id;
    private String name;
    private Integer capacity;
}

