package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.ClassesStudent;
import com.bigcenter.app.entities.ClassesStudentId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesStudentRepository extends JpaRepository<ClassesStudent, ClassesStudentId> {
}