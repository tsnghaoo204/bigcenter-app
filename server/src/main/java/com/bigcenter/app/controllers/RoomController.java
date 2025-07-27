package com.bigcenter.app.controllers;

import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.dtos.responses.RoomResponseDTO;
import com.bigcenter.app.services.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms(
            @RequestParam(name = "_start", defaultValue = "0") int start,
            @RequestParam(name = "_end", defaultValue = "10") int end
    ) {
        List<RoomResponseDTO> allRooms = roomService.getAllRoom();
        int total = allRooms.size();

        int toIndex = Math.min(end, total);
        List<RoomResponseDTO> page = allRooms.subList(start, toIndex);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Range", "rooms " + start + "-" + (toIndex - 1) + "/" + total);
        headers.add("Access-Control-Expose-Headers", "Content-Range");

        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/{roomName}")
    public ResponseEntity<RoomResponseDTO> getRoomByName(@PathVariable String roomName) {
        RoomResponseDTO room = roomService.getRoom(roomName);
        return ResponseEntity.ok(room);
    }

    @PutMapping
    public ResponseEntity<RoomResponseDTO> updateRoom(@RequestBody UpdateRoomDTO dto) {
        RoomResponseDTO updatedRoom = roomService.updateRoom(dto);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable UUID id) {
        roomService.deleteRoom(id);
        return ResponseEntity.ok("Room deleted successfully.");
    }
}

