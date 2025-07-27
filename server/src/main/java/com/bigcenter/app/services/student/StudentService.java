package com.bigcenter.app.services.student;

import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Student;

import java.util.List;
import java.util.UUID;

public interface StudentService {
    String createStudent(CreateStudentDTO dto);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudent(UUID studentCode);
    StudentResponseDTO updateStudent(UpdateStudentDTO dto);
    void deleteStudent(UUID studentCode);
}

