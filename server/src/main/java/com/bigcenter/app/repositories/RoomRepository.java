package com.bigcenter.app.repositories;

import com.bigcenter.app.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    Optional<Room> findById(UUID id);
    Boolean existsByName(String name);
    Optional<Room> findByName(String name);
}
