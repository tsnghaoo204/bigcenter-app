package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, UUID> {

    Optional<Class> findByName(String name);

    Boolean existsByName(String name);

    // Custom query để tránh duplicate issue khi fetch với OneToOne
    @Query("SELECT c FROM Class c")
    List<Class> findAllClasses();

    // Query với JOIN FETCH cho OneToOne relationship
    @Query("SELECT c FROM Class c LEFT JOIN FETCH c.subject LEFT JOIN FETCH c.classesStudents")
    List<Class> findAllWithRelations();

    // Query để kiểm tra duplicate
    @Query("SELECT c.id, COUNT(c) FROM Class c GROUP BY c.id HAVING COUNT(c) > 1")
    List<Object[]> findDuplicateIds();
}