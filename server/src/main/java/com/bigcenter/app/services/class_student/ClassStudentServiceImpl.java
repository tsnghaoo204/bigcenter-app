package com.bigcenter.app.services.class_student;

import com.bigcenter.app.dtos.mappers.ClassMapper;
import com.bigcenter.app.dtos.mappers.StudentMapper;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.ClassesStudent;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.id.ClassesStudentId;
import com.bigcenter.app.repositories.ClassRepository;
import com.bigcenter.app.repositories.ClassesStudentRepository;
import com.bigcenter.app.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClassStudentServiceImpl implements ClassesStudentService{
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final ClassesStudentRepository classesStudentRepository;
    private final StudentMapper studentMapper;
    private final ClassMapper classMapper;

    public void enrollStudent(UUID studentId, UUID classId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Class clazz = classRepository.findById(classId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        ClassesStudentId id = new ClassesStudentId(classId, studentId);
        if (classesStudentRepository.existsById(id)) {
            throw new RuntimeException("Student already enrolled in this class");
        }

        ClassesStudent enrollment = new ClassesStudent();
        enrollment.setId(id);
        enrollment.setStudent(student);
        enrollment.setClassField(clazz);
        classesStudentRepository.save(enrollment);
    }

    public void removeStudentFromClass(UUID studentId, UUID classId) {
        ClassesStudentId id = new ClassesStudentId(classId, studentId);
        classesStudentRepository.deleteById(id);
    }

    public List<StudentResponseDTO> getStudentsInClass(UUID classId) {
        return studentMapper.toResponseDTOList(classesStudentRepository.findStudentsByClassId(classId));
    }

    public List<ClassResponseDTO> getClassesOfStudent(UUID studentId) {
        return classMapper.toResponseDTOList(classesStudentRepository.findClassesByStudentId(studentId));
    }
}
