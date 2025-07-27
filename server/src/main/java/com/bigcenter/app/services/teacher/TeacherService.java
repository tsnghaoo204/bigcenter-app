package com.bigcenter.app.services.teacher;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.dtos.responses.TeacherResponseDTO;
import com.bigcenter.app.entities.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    String createTeacher(CreateTeacherDTO dto);
    List<TeacherResponseDTO> getAllTeachers();
    TeacherResponseDTO getTeacher(UUID teacherCode);
    TeacherResponseDTO updateTeacher(UpdateTeacherDTO dto);
    void deleteTeacher(UUID teacherCode);
}
