package com.bigcenter.app.services.room;

import com.bigcenter.app.dtos.mappers.RoomMapper;
import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.dtos.responses.RoomResponseDTO;
import com.bigcenter.app.entities.Room;
import com.bigcenter.app.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Override
    public String createRoom(CreateRoomDTO dto) {
        if (roomRepository.existsByName(dto.getName())) {
            throw new RuntimeException("Room already exists");
        }
        roomRepository.save(roomMapper.toEntity(dto));
        return "Room created successfully.";
    }

    @Override
    public List<RoomResponseDTO> getAllRoom() {
        return roomMapper.toResponseDTOList(roomRepository.findAll());
    }

    @Override
    public RoomResponseDTO getRoom(String roomName) {
        Room room = roomRepository.findByName(roomName)
                .orElseThrow(() -> new RuntimeException("Room not found: " + roomName));
        return roomMapper.toResponseDTO(room);
    }

    @Override
    public RoomResponseDTO updateRoom(UpdateRoomDTO dto) {
        Room existing = roomRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        existing.setName(dto.getName());
        existing.setCapacity(dto.getCapacity());
        roomRepository.save(existing);
        return roomMapper.toResponseDTO(existing);
    }

    @Override
    public void deleteRoom(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
        roomRepository.delete(room);
    }
}
