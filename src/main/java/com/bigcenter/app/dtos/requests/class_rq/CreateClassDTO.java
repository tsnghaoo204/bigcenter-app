package com.bigcenter.app.dtos.requests.class_rq;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreateClassDTO {
    private String name;
    private String subject;
    private LocalDate startDate;
    private LocalDate endDate;
}
