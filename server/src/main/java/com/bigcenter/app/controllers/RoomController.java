package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.entities.Room;
import com.bigcenter.app.services.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<String> createRoom(@RequestBody CreateRoomDTO dto) {
        String message = roomService.createRoom(dto);
        return ResponseEntity.ok(message);
    }

    @GetMapping
    public ResponseEntity<Set<Room>> getAllRooms() {
        Set<Room> rooms = roomService.getAllRoom();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<Room> getRoomByName(@PathVariable String roomName) {
        Room room = roomService.getRoom(roomName);
        return ResponseEntity.ok(room);
    }

    @PutMapping
    public ResponseEntity<Room> updateRoom(@RequestBody UpdateRoomDTO dto) {
        Room updatedRoom = roomService.updateRoom(dto);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully.");
    }
}

