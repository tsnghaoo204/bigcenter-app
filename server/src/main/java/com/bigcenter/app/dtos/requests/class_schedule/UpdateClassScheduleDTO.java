package com.bigcenter.app.dtos.requests.class_schedule;

import com.bigcenter.app.status.ClassStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class UpdateClassScheduleDTO {
    private Integer id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private UUID roomId;
    private UUID teacherId;

    private ClassStatus status;
}

