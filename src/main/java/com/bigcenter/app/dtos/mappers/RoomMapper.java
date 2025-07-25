package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.room.CreateRoomDTO;
import com.bigcenter.app.dtos.requests.room.UpdateRoomDTO;
import com.bigcenter.app.dtos.responses.RoomResponseDTO;
import com.bigcenter.app.entities.Room;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(source = "tenantId", target = "tenant.id")
    Room toEntity(CreateRoomDTO dto);

    @Mapping(source = "tenant.id", target = "tenantId")
    @Mapping(source = "tenant.name", target = "tenantName")
    RoomResponseDTO toResponseDTO(Room room);

    List<RoomResponseDTO> toResponseDTOList(List<Room> list);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateRoomDTO dto, @MappingTarget Room room);
}
