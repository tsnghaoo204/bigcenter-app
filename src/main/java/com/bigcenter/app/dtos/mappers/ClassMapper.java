package com.bigcenter.app.dtos.mappers;


import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClassMapper {

    Class toEntity(CreateClassDTO dto);

    ClassResponseDTO toResponseDTO(Class entity);

    List<ClassResponseDTO> toResponseDTOList(List<Class> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateClassDTO dto, @MappingTarget Class entity);
}