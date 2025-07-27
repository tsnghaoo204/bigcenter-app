package com.bigcenter.app.services.class_schedule;

import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.entities.ClassSchedule;

import java.util.Set;

public interface ClassScheduleService {
    String createSchedule(CreateClassScheduleDTO dto);
    Set<ClassSchedule> getAllSchedules();
    ClassSchedule getSchedule(int id);
    ClassSchedule updateSchedule(UpdateClassScheduleDTO dto);
    void deleteSchedule(int id);
}
