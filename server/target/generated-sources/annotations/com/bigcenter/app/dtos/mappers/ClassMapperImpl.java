package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.requests.class_rq.UpdateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-26T23:18:49+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class ClassMapperImpl implements ClassMapper {

    @Override
    public Class toEntity(CreateClassDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setName( dto.getName() );
        class1.setSubject( dto.getSubject() );
        class1.setStartDate( dto.getStartDate() );
        class1.setEndDate( dto.getEndDate() );

        return class1;
    }

    @Override
    public ClassResponseDTO toResponseDTO(Class entity) {
        if ( entity == null ) {
            return null;
        }

        ClassResponseDTO classResponseDTO = new ClassResponseDTO();

        classResponseDTO.setId( entity.getId() );
        classResponseDTO.setName( entity.getName() );
        classResponseDTO.setSubject( entity.getSubject() );
        classResponseDTO.setStartDate( entity.getStartDate() );
        classResponseDTO.setEndDate( entity.getEndDate() );

        return classResponseDTO;
    }

    @Override
    public List<ClassResponseDTO> toResponseDTOList(List<Class> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ClassResponseDTO> list = new ArrayList<ClassResponseDTO>( entities.size() );
        for ( Class class1 : entities ) {
            list.add( toResponseDTO( class1 ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UpdateClassDTO dto, Class entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            entity.setName( dto.getName() );
        }
        if ( dto.getSubject() != null ) {
            entity.setSubject( dto.getSubject() );
        }
        if ( dto.getStartDate() != null ) {
            entity.setStartDate( dto.getStartDate() );
        }
        if ( dto.getEndDate() != null ) {
            entity.setEndDate( dto.getEndDate() );
        }
    }
}
