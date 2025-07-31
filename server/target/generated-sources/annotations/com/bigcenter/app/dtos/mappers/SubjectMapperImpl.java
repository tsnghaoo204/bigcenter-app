package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.subject.SubjectDto;
import com.bigcenter.app.dtos.responses.SubjectResponseDTO;
import com.bigcenter.app.entities.Subject;
import com.bigcenter.app.entities.Teacher;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-01T05:05:51+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class SubjectMapperImpl implements SubjectMapper {

    @Override
    public SubjectResponseDTO toDto(Subject subject) {
        if ( subject == null ) {
            return null;
        }

        SubjectResponseDTO subjectResponseDTO = new SubjectResponseDTO();

        subjectResponseDTO.setTeacherId( subjectTeacherId( subject ) );
        subjectResponseDTO.setId( subject.getId() );
        subjectResponseDTO.setSubjectName( subject.getSubjectName() );

        return subjectResponseDTO;
    }

    @Override
    public Subject toSubject(SubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setTeacher( subjectDtoToTeacher( subjectDto ) );
        subject.setSubjectName( subjectDto.getSubjectName() );

        return subject;
    }

    private UUID subjectTeacherId(Subject subject) {
        if ( subject == null ) {
            return null;
        }
        Teacher teacher = subject.getTeacher();
        if ( teacher == null ) {
            return null;
        }
        UUID id = teacher.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Teacher subjectDtoToTeacher(SubjectDto subjectDto) {
        if ( subjectDto == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setId( subjectDto.getTeacherId() );

        return teacher;
    }
}
