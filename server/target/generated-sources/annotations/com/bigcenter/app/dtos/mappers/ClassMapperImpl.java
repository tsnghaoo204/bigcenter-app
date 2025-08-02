package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.class_rq.CreateClassDTO;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-03T00:08:00+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.2.1 (Oracle Corporation)"
)
@Component
public class ClassMapperImpl implements ClassMapper {

    @Override
    public Class toEntity(CreateClassDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setSubject( createClassDTOToSubject( dto ) );
        class1.setName( dto.getName() );
        class1.setStartDate( dto.getStartDate() );
        class1.setEndDate( dto.getEndDate() );
        class1.setRoom( dto.getRoom() );

        return class1;
    }

    @Override
    public ClassResponseDTO toResponseDTO(Class entity) {
        if ( entity == null ) {
            return null;
        }

        ClassResponseDTO classResponseDTO = new ClassResponseDTO();

        UUID id = entitySubjectId( entity );
        if ( id != null ) {
            classResponseDTO.setSubjectId( id.toString() );
        }
        classResponseDTO.setId( entity.getId() );
        classResponseDTO.setName( entity.getName() );
        classResponseDTO.setStartDate( entity.getStartDate() );
        classResponseDTO.setEndDate( entity.getEndDate() );
        classResponseDTO.setRoom( entity.getRoom() );

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

    protected Subject createClassDTOToSubject(CreateClassDTO createClassDTO) {
        if ( createClassDTO == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( createClassDTO.getSubjectId() );

        return subject;
    }

    private UUID entitySubjectId(Class class1) {
        if ( class1 == null ) {
            return null;
        }
        Subject subject = class1.getSubject();
        if ( subject == null ) {
            return null;
        }
        UUID id = subject.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
