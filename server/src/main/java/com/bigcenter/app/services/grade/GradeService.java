package com.bigcenter.app.services.grade;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Grade;

public interface GradeService {
    String saveGrade(GradeDto grade);
}
