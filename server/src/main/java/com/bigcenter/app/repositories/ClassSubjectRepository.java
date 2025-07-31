package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.ClassSubject;
import com.bigcenter.app.entities.id.ClassSubjectId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassSubjectRepository extends JpaRepository<ClassSubject, ClassSubjectId> {
}