package com.bigcenter.app.dtos.responses;

import com.bigcenter.app.status.ClassStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
public class ClassScheduleResponseDTO {
    private Integer id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    private UUID classId;
    private String className;

    private UUID roomId;
    private String roomName;

    private UUID teacherId;
    private String teacherName;

    private ClassStatus status;
}
