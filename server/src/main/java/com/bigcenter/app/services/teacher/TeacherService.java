package com.bigcenter.app.services.teacher;

import com.bigcenter.app.dtos.requests.teacher.CreateTeacherDTO;
import com.bigcenter.app.dtos.requests.teacher.UpdateTeacherDTO;
import com.bigcenter.app.entities.Teacher;

import java.util.Set;
import java.util.UUID;

public interface TeacherService {
    String createTeacher(CreateTeacherDTO dto);
    Set<Teacher> getAllTeachers();
    Teacher getTeacher(UUID teacherCode);
    Teacher updateTeacher(UpdateTeacherDTO dto);
    void deleteTeacher(UUID teacherCode);
}
