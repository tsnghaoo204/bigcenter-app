package com.bigcenter.app.services.class_schedule;

import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.dtos.responses.ClassScheduleResponseDTO;
import com.bigcenter.app.entities.ClassSchedule;

import java.util.List;

public interface ClassScheduleService {
    String createSchedule(CreateClassScheduleDTO dto);
    List<ClassScheduleResponseDTO> getAllSchedules();
    ClassScheduleResponseDTO getSchedule(int id);
    ClassScheduleResponseDTO updateSchedule(UpdateClassScheduleDTO dto);
    void deleteSchedule(int id);
}
