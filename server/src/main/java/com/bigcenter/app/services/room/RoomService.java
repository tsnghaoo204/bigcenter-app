package com.bigcenter.app.services.room;

import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.dtos.responses.RoomResponseDTO;
import com.bigcenter.app.entities.Room;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RoomService {
    String createRoom(CreateRoomDTO dto);
    List<RoomResponseDTO> getAllRoom();
    RoomResponseDTO getRoom(String roomName);
    RoomResponseDTO updateRoom(UpdateRoomDTO dto);
    void deleteRoom(UUID id);
}
