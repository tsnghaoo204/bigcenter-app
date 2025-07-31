package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.subject.SubjectDto;
import com.bigcenter.app.dtos.responses.SubjectResponseDTO;
import com.bigcenter.app.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectMapper {
    @Mapping(source = "teacher.id", target = "teacherId")
    SubjectResponseDTO toDto(Subject subject);

    @Mapping(source = "teacherId", target = "teacher.id")
    Subject toSubject(SubjectDto subjectDto);
}
