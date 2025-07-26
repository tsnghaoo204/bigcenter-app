package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.dtos.responses.TeacherResponseDTO;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T00:02:12+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toEntity(CreateTeacherDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setUser( createTeacherDTOToUser( dto ) );
        teacher.setSpecialization( dto.getSpecialization() );
        teacher.setPhone( dto.getPhone() );

        return teacher;
    }

    @Override
    public TeacherResponseDTO toResponseDTO(Teacher entity) {
        if ( entity == null ) {
            return null;
        }

        TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO();

        teacherResponseDTO.setUserId( entityUserId( entity ) );
        teacherResponseDTO.setTeacherName( entityUserFullName( entity ) );
        teacherResponseDTO.setId( entity.getId() );
        teacherResponseDTO.setSpecialization( entity.getSpecialization() );
        teacherResponseDTO.setPhone( entity.getPhone() );

        return teacherResponseDTO;
    }

    @Override
    public List<TeacherResponseDTO> toResponseDTOList(List<Teacher> teachers) {
        if ( teachers == null ) {
            return null;
        }

        List<TeacherResponseDTO> list = new ArrayList<TeacherResponseDTO>( teachers.size() );
        for ( Teacher teacher : teachers ) {
            list.add( toResponseDTO( teacher ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UpdateTeacherDTO dto, Teacher entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getSpecialization() != null ) {
            entity.setSpecialization( dto.getSpecialization() );
        }
        if ( dto.getPhone() != null ) {
            entity.setPhone( dto.getPhone() );
        }
    }

    protected User createTeacherDTOToUser(CreateTeacherDTO createTeacherDTO) {
        if ( createTeacherDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( createTeacherDTO.getUserId() );

        return user;
    }

    private UUID entityUserId(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityUserFullName(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }
        User user = teacher.getUser();
        if ( user == null ) {
            return null;
        }
        String fullName = user.getFullName();
        if ( fullName == null ) {
            return null;
        }
        return fullName;
    }
}
