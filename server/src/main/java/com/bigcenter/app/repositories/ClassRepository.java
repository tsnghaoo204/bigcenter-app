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

}