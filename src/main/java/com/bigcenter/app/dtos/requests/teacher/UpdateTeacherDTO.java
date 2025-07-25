package com.bigcenter.app.dtos.requests.teacher;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateTeacherDTO {
    private UUID id;
    private String specialization;
    private String phone;
}
