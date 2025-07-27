package com.bigcenter.app.dtos.responses;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class StudentResponseDTO {
    private UUID id;
    private LocalDate dob;
    private String phone;
    private String guardianInf;
    private UUID userId;
    private String studentName;
}
