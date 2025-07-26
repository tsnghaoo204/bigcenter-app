package com.bigcenter.app.dtos.requests.student;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateStudentDTO {
    private LocalDate dob;
    private String phone;
    private String guardianInf;
    private UUID userId;
}
