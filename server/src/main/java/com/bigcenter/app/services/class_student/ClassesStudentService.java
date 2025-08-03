package com.bigcenter.app.services.class_student;

import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.Student;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

public interface ClassesStudentService {
    void enrollStudent(UUID studentId, UUID classId);
    void removeStudentFromClass(UUID studentId, UUID classId);
    List<StudentResponseDTO> getStudentsInClass(UUID classId);
    List<ClassResponseDTO> getClassesOfToken(Jwt jwt);
}
