package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
}