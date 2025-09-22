package com.bigcenter.app.services.class_student;

import com.bigcenter.app.dtos.mappers.ClassMapper;
import com.bigcenter.app.dtos.mappers.StudentMapper;
import com.bigcenter.app.dtos.responses.ClassResponseDTO;
import com.bigcenter.app.dtos.responses.StudentResponseDTO;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.ClassesStudent;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.User;
import com.bigcenter.app.entities.id.ClassesStudentId;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.ClassRepository;
import com.bigcenter.app.repositories.ClassesStudentRepository;
import com.bigcenter.app.repositories.StudentRepository;
import com.bigcenter.app.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
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
    private final UserRepository userRepository;

    public void enrollStudent(UUID studentId, UUID classId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Class clazz = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));

        ClassesStudentId id = new ClassesStudentId(classId, studentId);
        if (classesStudentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Student already enrolled in this class");
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

    @Override
    public List<ClassResponseDTO> getClassesOfToken(Jwt jwt) {
        String sub = jwt.getClaim("sub");
        User user = userRepository.findByCognitoSub(sub)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Student student = studentRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found!"));
        List<Class> classList = classesStudentRepository.findClassesByStudentId(student.getId());
        return classMapper.toResponseDTOList(classList);
    }

    public List<StudentResponseDTO> getStudentsInClass(UUID classId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        for (GrantedAuthority authority : authentication.getAuthorities()) {
            System.out.println(authority.getAuthority());
        }
        return studentMapper.toResponseDTOList(classesStudentRepository.findStudentsByClassId(classId));
    }
}
