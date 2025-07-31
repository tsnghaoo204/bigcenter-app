package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}