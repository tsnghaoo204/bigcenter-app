package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.dtos.responses.TeacherResponseDTO;
import com.bigcenter.app.entities.Teacher;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "userId", target = "user.id")
    Teacher toEntity(CreateTeacherDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "teacherName")
    TeacherResponseDTO toResponseDTO(Teacher entity);

    List<TeacherResponseDTO> toResponseDTOList(List<Teacher> teachers);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateTeacherDTO dto, @MappingTarget Teacher entity);
}
