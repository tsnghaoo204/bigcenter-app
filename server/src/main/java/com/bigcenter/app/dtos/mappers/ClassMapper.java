package com.bigcenter.app.dtos.mappers;


import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    @Mapping(source = "subjectId", target = "subject.id")
    Class toEntity(CreateClassDTO dto);

    @Mapping(source = "subject.id", target = "subjectId")
    ClassResponseDTO toResponseDTO(Class entity);

    List<ClassResponseDTO> toResponseDTOList(List<Class> entities);
}