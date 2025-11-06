package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import com.cdl.escrow.entity.ManagementFirmFinancialSummary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ManagementFirmMapper.class })
public interface ManagementFirmFinancialSummaryMapper extends EntityMapper<ManagementFirmFinancialSummaryDTO, ManagementFirmFinancialSummary> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    ManagementFirmFinancialSummaryDTO toDto(ManagementFirmFinancialSummary managementFirmFinancialSummary);

    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    ManagementFirmFinancialSummary toEntity(ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO);
}
