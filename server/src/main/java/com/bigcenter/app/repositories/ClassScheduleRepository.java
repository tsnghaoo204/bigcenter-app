package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.ClassSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassScheduleRepository extends JpaRepository<ClassSchedule, Integer> {
}