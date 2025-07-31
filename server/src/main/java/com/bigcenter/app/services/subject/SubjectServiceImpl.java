package com.bigcenter.app.services.subject;

import com.bigcenter.app.dtos.mappers.SubjectMapper;
import com.bigcenter.app.dtos.requests.subject.SubjectDto;
import com.bigcenter.app.dtos.responses.SubjectResponseDTO;
import com.bigcenter.app.entities.Subject;
import com.bigcenter.app.repositories.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectMapper subjectMapper;
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectMapper subjectMapper, SubjectRepository subjectRepository) {
        this.subjectMapper = subjectMapper;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public String addSubject(SubjectDto subject) {
        Subject nSubject = subjectMapper.toSubject(subject);
        subjectRepository.save(nSubject);
        return "Subject added successfully";
    }

    @Override
    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubject(UUID subjectId) {
        subjectRepository.deleteById(subjectId);
    }
}
