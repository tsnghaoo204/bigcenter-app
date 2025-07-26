package com.bigcenter.app.dtos.requests.teacher;

import lombok.Data;

import java.util.UUID;

@Data
public class CreateTeacherDTO {
    private String specialization;
    private String phone;
    private UUID userId;
}
