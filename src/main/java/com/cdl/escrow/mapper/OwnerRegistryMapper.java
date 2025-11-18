package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.entity.OwnerRegistry;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ApplicationSettingMapper.class, OwnerRegistryUnitMapper.class, TaskStatusMapper.class})
public interface OwnerRegistryMapper extends EntityMapper<OwnerRegistryDTO, OwnerRegistry> {

    @Mapping(source = "documentType", target = "documentTypeDTO")
    @Mapping(source = "countryOption", target = "countryOptionDTO")
    @Mapping(source = "investorType", target = "investorTypeDTO")
    @Mapping(source = "ownerRegistryUnit", target = "ownerRegistryUnitDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    OwnerRegistryDTO toDto(OwnerRegistry ownerRegistry);


    @Mapping(source = "documentTypeDTO", target = "documentType")
    @Mapping(source = "countryOptionDTO", target = "countryOption")
    @Mapping(source = "investorTypeDTO", target = "investorType")
    @Mapping(source = "ownerRegistryUnitDTO", target = "ownerRegistryUnit")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    OwnerRegistry toEntity(OwnerRegistryDTO ownerRegistryDTO);
}
