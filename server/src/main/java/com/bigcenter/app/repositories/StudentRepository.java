package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Student;
import com.bigcenter.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    Optional<Student> findById(UUID id);
    Boolean existsByUser(User user);
    @Modifying
    @Query(value = "DELETE FROM students WHERE user_id = ?1", nativeQuery = true)
    void deleteByUserId(UUID userId);

    Optional<Student> findByUser(User user);

}