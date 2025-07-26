package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Student;
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
public class StudentMapperImpl implements StudentMapper {

    @Override
    public Student toEntity(CreateStudentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Student student = new Student();

        student.setUser( createStudentDTOToUser( dto ) );
        student.setDob( dto.getDob() );
        student.setPhone( dto.getPhone() );
        student.setGuardianInf( dto.getGuardianInf() );

        return student;
    }

    @Override
    public StudentResponseDTO toResponseDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setUserId( studentUserId( student ) );
        studentResponseDTO.setStudentName( studentUserFullName( student ) );
        studentResponseDTO.setId( student.getId() );
        studentResponseDTO.setDob( student.getDob() );
        studentResponseDTO.setPhone( student.getPhone() );
        studentResponseDTO.setGuardianInf( student.getGuardianInf() );

        return studentResponseDTO;
    }

    @Override
    public List<StudentResponseDTO> toResponseDTOList(List<Student> students) {
        if ( students == null ) {
            return null;
        }

        List<StudentResponseDTO> list = new ArrayList<StudentResponseDTO>( students.size() );
        for ( Student student : students ) {
            list.add( toResponseDTO( student ) );
        }

        return list;
    }

    @Override
    public void updateEntity(UpdateStudentDTO dto, Student student) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            student.setId( dto.getId() );
        }
        if ( dto.getDob() != null ) {
            student.setDob( dto.getDob() );
        }
        if ( dto.getPhone() != null ) {
            student.setPhone( dto.getPhone() );
        }
        if ( dto.getGuardianInf() != null ) {
            student.setGuardianInf( dto.getGuardianInf() );
        }
    }

    protected User createStudentDTOToUser(CreateStudentDTO createStudentDTO) {
        if ( createStudentDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( createStudentDTO.getUserId() );

        return user;
    }

    private UUID studentUserId(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String studentUserFullName(Student student) {
        if ( student == null ) {
            return null;
        }
        User user = student.getUser();
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
