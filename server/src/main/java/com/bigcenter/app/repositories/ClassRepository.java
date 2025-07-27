package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Class;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClassRepository extends JpaRepository<Class, UUID> {
    Optional<Class> findByName(String name);
    Optional<Class> findById(UUID id);
    Boolean existsByName(String name);
}