package com.bigcenter.app.services.teacher;

import com.bigcenter.app.dtos.mappers.TeacherMapper;
import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public String createTeacher(CreateTeacherDTO dto) {
        teacherRepository.save(teacherMapper.toEntity(dto));
        return "Teacher created!";
    }

    @Override
    public Set<Teacher> getAllTeachers() {
        return new HashSet<>(teacherRepository.findAll());
    }

    @Override
    public Teacher getTeacher(UUID teacherCode) {
        return teacherRepository.findById(teacherCode)
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));
    }

    @Override
    public Teacher updateTeacher(UpdateTeacherDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getId())
                .orElseThrow(() -> new NoSuchElementException("Teacher not found"));

        teacher.setSpecialization(dto.getSpecialization());
        teacher.setPhone(dto.getPhone());
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(UUID teacherCode) {
        teacherRepository.deleteById(teacherCode);
    }
}

