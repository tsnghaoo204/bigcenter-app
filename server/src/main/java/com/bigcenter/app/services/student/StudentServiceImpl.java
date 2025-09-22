package com.bigcenter.app.services.student;

import com.bigcenter.app.dtos.mappers.StudentMapper;
import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public String createStudent(CreateStudentDTO dto) {

        studentRepository.save(studentMapper.toEntity(dto));
        return "Student created!";
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        return studentMapper.toResponseDTOList(studentRepository.findAll());
    }

    @Override
    public StudentResponseDTO getStudent(UUID studentCode) {
        Student student = studentRepository.findById(studentCode)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return studentMapper.toResponseDTO(student);
    }

    @Override
    public StudentResponseDTO updateStudent(UpdateStudentDTO dto) {
        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        student.setDob(dto.getDob());
        student.setPhone(dto.getPhone());
        student.setGuardianInf(dto.getGuardianInf());
        studentRepository.save(student);
        return studentMapper.toResponseDTO(student);
    }

    @Override
    public void deleteStudent(UUID studentCode) {
        studentRepository.deleteById(studentCode);
    }
}

