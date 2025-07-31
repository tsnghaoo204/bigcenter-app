package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "classField.id", target = "classId")
    @Mapping(source = "subject.id", target = "subjectId")
    GradeDto toDto(Grade grade);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "classId", target = "classField.id")
    @Mapping(source = "subjectId", target = "subject.id")
    Grade toEntity(GradeDto dto);
}
