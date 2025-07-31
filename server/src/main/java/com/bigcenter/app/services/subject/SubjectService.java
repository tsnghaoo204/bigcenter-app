package com.bigcenter.app.services.subject;

import com.bigcenter.app.dtos.requests.subject.SubjectDto;
import com.bigcenter.app.dtos.responses.SubjectResponseDTO;
import com.bigcenter.app.entities.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectService {
    String addSubject(SubjectDto subject);
    List<SubjectResponseDTO> getAllSubjects();
    void deleteSubject(UUID subjectId);
}
