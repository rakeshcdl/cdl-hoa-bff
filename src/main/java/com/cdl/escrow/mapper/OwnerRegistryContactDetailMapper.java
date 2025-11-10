package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryContactDetailDTO;
import com.cdl.escrow.entity.OwnerRegistryContactDetail;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { OwnerRegistryMapper.class})
public interface OwnerRegistryContactDetailMapper  extends EntityMapper<OwnerRegistryContactDetailDTO, OwnerRegistryContactDetail> {

    @Mapping(source = "ownerRegistry", target = "ownerRegistryDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    OwnerRegistryContactDetailDTO toDto(OwnerRegistryContactDetail ownerRegistryContactDetail);


    @Mapping(source = "ownerRegistryDTO", target = "ownerRegistry")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    OwnerRegistryContactDetail toEntity(OwnerRegistryContactDetailDTO ownerRegistryContactDetailDTO);
}
