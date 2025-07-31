package com.bigcenter.app.dtos.responses;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class ClassResponseDTO {
    private UUID id;
    private String name;
    private String subjectId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String room;

}