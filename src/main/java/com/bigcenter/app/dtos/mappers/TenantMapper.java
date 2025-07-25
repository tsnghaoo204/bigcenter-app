package com.bigcenter.app.dtos.mappers;

import com.bigcenter.app.dtos.requests.tenant.CreateTenantDTO;
import com.bigcenter.app.dtos.requests.tenant.UpdateTenantDTO;
import com.bigcenter.app.dtos.responses.TenantResponseDTO;
import com.bigcenter.app.entities.Tenant;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TenantMapper {

    Tenant toEntity(CreateTenantDTO dto);

    TenantResponseDTO toResponseDTO(Tenant tenant);

    List<TenantResponseDTO> toResponseDTOList(List<Tenant> tenants);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(UpdateTenantDTO dto, @MappingTarget Tenant tenant);
}

