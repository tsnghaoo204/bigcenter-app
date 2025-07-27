package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.dtos.responses.RoomResponseDTO;
import com.bigcenter.app.entities.Room;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-27T19:20:14+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.2 (Amazon.com Inc.)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toEntity(CreateRoomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Room room = new Room();

        room.setName( dto.getName() );
        room.setCapacity( dto.getCapacity() );

        return room;
    }

    @Override
    public RoomResponseDTO toResponseDTO(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();

        roomResponseDTO.setId( room.getId() );
        roomResponseDTO.setName( room.getName() );
        roomResponseDTO.setCapacity( room.getCapacity() );

        return roomResponseDTO;
    }

    @Override
    public List<RoomResponseDTO> toResponseDTOList(List<Room> list) {
        if ( list == null ) {
            return null;
        }

        List<RoomResponseDTO> list1 = new ArrayList<RoomResponseDTO>( list.size() );
        for ( Room room : list ) {
            list1.add( toResponseDTO( room ) );
        }

        return list1;
    }

    @Override
    public void updateEntity(UpdateRoomDTO dto, Room room) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            room.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            room.setName( dto.getName() );
        }
        if ( dto.getCapacity() != null ) {
            room.setCapacity( dto.getCapacity() );
        }
    }
}
