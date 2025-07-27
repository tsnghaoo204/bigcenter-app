package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findById(UUID id);
    Boolean existsByUser(User user);
    void deleteByUser(User user);
}