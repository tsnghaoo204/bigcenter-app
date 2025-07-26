package com.bigcenter.app.dtos.mappers;


import com.bigcenter.app.dtos.requests.class_schedule.CreateClassScheduleDTO;
import com.bigcenter.app.dtos.requests.class_schedule.UpdateClassScheduleDTO;
import com.bigcenter.app.dtos.responses.ClassScheduleResponseDTO;
import com.bigcenter.app.entities.ClassSchedule;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassScheduleMapper {

    @Mapping(source = "classId", target = "classField.id")
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "teacherId", target = "teacher.id")
    ClassSchedule toEntity(CreateClassScheduleDTO dto);

    @Mapping(source = "classField.id", target = "classId")
    @Mapping(source = "classField.name", target = "className")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "teacher.user.fullName", target = "teacherName")
    ClassScheduleResponseDTO toResponseDTO(ClassSchedule schedule);

    List<ClassScheduleResponseDTO> toResponseDTOList(List<ClassSchedule> list);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateClassScheduleDTO dto, @MappingTarget ClassSchedule schedule);
}

