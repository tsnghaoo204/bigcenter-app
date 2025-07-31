package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Class;
import com.bigcenter.app.entities.Grade;
import com.bigcenter.app.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    boolean existsByStudentAndClassField(Student student, Class clazz);
}