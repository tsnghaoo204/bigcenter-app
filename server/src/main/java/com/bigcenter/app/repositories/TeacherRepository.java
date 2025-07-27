package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Teacher;
import com.bigcenter.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    Optional<Teacher> findById(UUID id);
    Boolean existsByUser(User user);
    void deleteByUser(User user);
}