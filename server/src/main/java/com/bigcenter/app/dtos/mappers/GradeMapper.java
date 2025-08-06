package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    GradeDto toDto(Grade grade);

    Grade toEntity(GradeDto dto);
}
