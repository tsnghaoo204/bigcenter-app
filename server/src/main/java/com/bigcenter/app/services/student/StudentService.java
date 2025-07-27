package com.bigcenter.app.services.student;

import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.entities.Student;

import java.util.Set;
import java.util.UUID;

public interface StudentService {
    String createStudent(CreateStudentDTO dto);
    Set<Student> getAllStudents();
    Student getStudent(UUID studentCode);
    Student updateStudent(UpdateStudentDTO dto);
    void deleteStudent(UUID studentCode);
}

