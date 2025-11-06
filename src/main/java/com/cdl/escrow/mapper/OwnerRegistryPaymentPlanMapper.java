package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryPaymentPlanDTO;
import com.cdl.escrow.entity.OwnerRegistryPaymentPlan;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { OwnerRegistryMapper.class })
public interface OwnerRegistryPaymentPlanMapper extends EntityMapper<OwnerRegistryPaymentPlanDTO, OwnerRegistryPaymentPlan> {

    @Mapping(source = "ownerRegistry", target = "ownerRegistryDTO")
    OwnerRegistryPaymentPlanDTO toDto(OwnerRegistryPaymentPlan ownerRegistryPaymentPlan);


    @Mapping(source = "ownerRegistryDTO", target = "ownerRegistry")
    OwnerRegistryPaymentPlan toEntity(OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO);
}
