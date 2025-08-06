package com.bigcenter.app.services.grade;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Grade;

import java.util.UUID;

public interface GradeService {
    String saveGrade(UUID studentId, UUID classId, UUID subjectID, GradeDto grade);
}
