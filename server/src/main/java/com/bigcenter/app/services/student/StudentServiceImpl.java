package com.bigcenter.app.services.student;

import com.bigcenter.app.dtos.mappers.StudentMapper;
import com.bigcenter.app.dtos.requests.student.CreateStudentDTO;
import com.bigcenter.app.dtos.requests.student.UpdateStudentDTO;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

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
    public Set<Student> getAllStudents() {
        return new HashSet<>(studentRepository.findAll());
    }

    @Override
    public Student getStudent(UUID studentCode) {
        return studentRepository.findById(studentCode)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));
    }

    @Override
    public Student updateStudent(UpdateStudentDTO dto) {
        Student student = studentRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        student.setDob(dto.getDob());
        student.setPhone(dto.getPhone());
        student.setGuardianInf(dto.getGuardianInf());
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(UUID studentCode) {
        studentRepository.deleteById(studentCode);
    }
}

