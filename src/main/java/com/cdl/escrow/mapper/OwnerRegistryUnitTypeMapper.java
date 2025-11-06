package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryUnitTypeDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitType;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerRegistryUnitTypeMapper extends EntityMapper<OwnerRegistryUnitTypeDTO, OwnerRegistryUnitType> {

    OwnerRegistryUnitTypeDTO toDto(OwnerRegistryUnitType ownerRegistryUnitType);
    OwnerRegistryUnitType toEntity(OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO);
}
