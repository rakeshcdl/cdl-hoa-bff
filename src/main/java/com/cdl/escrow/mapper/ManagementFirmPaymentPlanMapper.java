package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import com.cdl.escrow.entity.ManagementFirmPaymentPlan;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ManagementFirmMapper.class })
public interface ManagementFirmPaymentPlanMapper extends EntityMapper<ManagementFirmPaymentPlanDTO, ManagementFirmPaymentPlan> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    ManagementFirmPaymentPlanDTO toDto(ManagementFirmPaymentPlan managementFirmPaymentPlan);


    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    ManagementFirmPaymentPlan toEntity(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO);
}
