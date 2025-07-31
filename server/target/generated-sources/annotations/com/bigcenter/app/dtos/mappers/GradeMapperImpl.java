package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.grade.GradeDto;
import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.Grade;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.Subject;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-01T05:05:51+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class GradeMapperImpl implements GradeMapper {

    @Override
    public GradeDto toDto(Grade grade) {
        if ( grade == null ) {
            return null;
        }

        GradeDto gradeDto = new GradeDto();

        gradeDto.setStudentId( gradeStudentId( grade ) );
        gradeDto.setClassId( gradeClassFieldId( grade ) );
        gradeDto.setSubjectId( gradeSubjectId( grade ) );
        gradeDto.setScore( grade.getScore() );
        gradeDto.setMidtermScore( grade.getMidtermScore() );
        gradeDto.setFinalScore( grade.getFinalScore() );

        return gradeDto;
    }

    @Override
    public Grade toEntity(GradeDto dto) {
        if ( dto == null ) {
            return null;
        }

        Grade grade = new Grade();

        grade.setStudent( gradeDtoToStudent( dto ) );
        grade.setClassField( gradeDtoToClass( dto ) );
        grade.setSubject( gradeDtoToSubject( dto ) );
        grade.setScore( dto.getScore() );
        grade.setMidtermScore( dto.getMidtermScore() );
        grade.setFinalScore( dto.getFinalScore() );

        return grade;
    }

    private UUID gradeStudentId(Grade grade) {
        if ( grade == null ) {
            return null;
        }
        Student student = grade.getStudent();
        if ( student == null ) {
            return null;
        }
        UUID id = student.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID gradeClassFieldId(Grade grade) {
        if ( grade == null ) {
            return null;
        }
        Class classField = grade.getClassField();
        if ( classField == null ) {
            return null;
        }
        UUID id = classField.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private UUID gradeSubjectId(Grade grade) {
        if ( grade == null ) {
            return null;
        }
        Subject subject = grade.getSubject();
        if ( subject == null ) {
            return null;
        }
        UUID id = subject.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Student gradeDtoToStudent(GradeDto gradeDto) {
        if ( gradeDto == null ) {
            return null;
        }

        Student student = new Student();

        student.setId( gradeDto.getStudentId() );

        return student;
    }

    protected Class gradeDtoToClass(GradeDto gradeDto) {
        if ( gradeDto == null ) {
            return null;
        }

        Class class1 = new Class();

        class1.setId( gradeDto.getClassId() );

        return class1;
    }

    protected Subject gradeDtoToSubject(GradeDto gradeDto) {
        if ( gradeDto == null ) {
            return null;
        }

        Subject subject = new Subject();

        subject.setId( gradeDto.getSubjectId() );

        return subject;
    }
}
