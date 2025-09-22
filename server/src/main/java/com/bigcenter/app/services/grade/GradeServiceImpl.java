package com.bigcenter.app.services.grade;

import com.bigcenter.app.dtos.mappers.GradeMapper;
import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.Subject;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.ClassRepository;
import com.bigcenter.app.repositories.GradeRepository;
import com.bigcenter.app.repositories.StudentRepository;
import com.bigcenter.app.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final GradeMapper gradeMapper;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final SubjectRepository subjectRepository;

    @Override
    @Transactional
    public String saveGrade(UUID studentId, UUID classId, UUID subjectID, GradeDto grade) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        Class clazz = classRepository.findById(classId)
                .orElseThrow(() -> new ResourceNotFoundException("Class not found"));
        Subject subject = subjectRepository.findById(subjectID)
                        .orElseThrow(() -> new ResourceNotFoundException("Subject not found"));

        gradeRepository.save(gradeMapper.toEntity(grade));
        return "Save grade of " + student.getUser().getFullName() + " of " + subject.getSubjectName() + " in " + clazz.getName() + " successfully";
    }
}
