package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.ClassesStudent;
import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.id.ClassesStudentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ClassesStudentRepository extends JpaRepository<ClassesStudent, ClassesStudentId> {

    @Query("SELECT cs.classField FROM ClassesStudent cs WHERE cs.student.id = :studentId")
    List<Class> findClassesByStudentId(@Param("studentId") UUID studentId);

    @Query("SELECT cs.student FROM ClassesStudent cs WHERE cs.classField.id = :classId")
    List<Student> findStudentsByClassId(@Param("classId") UUID classId);

}