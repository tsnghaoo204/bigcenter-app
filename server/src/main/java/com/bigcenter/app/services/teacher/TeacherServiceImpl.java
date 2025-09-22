package com.bigcenter.app.services.teacher;

import com.bigcenter.app.dtos.mappers.TeacherMapper;
import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.dtos.responses.TeacherResponseDTO;
import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.payloads.exceptions.ResourceNotFoundException;
import com.bigcenter.app.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherMapper.toResponseDTOList(teacherRepository.findAll());
    }

    @Override
    public TeacherResponseDTO getTeacher(UUID teacherCode) {
        Teacher teacher = teacherRepository.findById(teacherCode)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
        return teacherMapper.toResponseDTO(teacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(UpdateTeacherDTO dto) {
        Teacher teacher = teacherRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        teacher.setSpecialization(dto.getSpecialization());
        teacher.setPhone(dto.getPhone());
        teacherRepository.save(teacher);
        return teacherMapper.toResponseDTO(teacher);
    }

    @Override
    public void deleteTeacher(UUID teacherCode) {
        teacherRepository.deleteById(teacherCode);
    }
}

