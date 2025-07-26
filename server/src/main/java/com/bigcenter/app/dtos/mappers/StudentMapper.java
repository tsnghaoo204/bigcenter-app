package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Student;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(source = "userId", target = "user.id")
    Student toEntity(CreateStudentDTO dto);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.fullName", target = "studentName")
    StudentResponseDTO toResponseDTO(Student student);

    List<StudentResponseDTO> toResponseDTOList(List<Student> students);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateStudentDTO dto, @MappingTarget Student student);
}
