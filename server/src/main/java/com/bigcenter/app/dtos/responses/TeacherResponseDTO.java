package com.bigcenter.app.dtos.responses;

import lombok.Data;

import java.util.UUID;

@Data
public class TeacherResponseDTO {
    private UUID id;
    private String specialization;
    private String phone;
    private String teacherName; // ← từ user.fullname
    private UUID userId;
}

