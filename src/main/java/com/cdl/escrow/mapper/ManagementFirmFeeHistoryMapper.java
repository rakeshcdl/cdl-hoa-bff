package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import com.cdl.escrow.entity.ManagementFirmHistory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class , ManagementFirmMapper.class, ManagementFirmFeeMapper.class})
public interface ManagementFirmFeeHistoryMapper extends EntityMapper<ManagementFirmHistoryDTO, ManagementFirmHistory> {

    @Mapping(source = "managementFirmFee", target = "managementFirmFeeDTO")
    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    ManagementFirmHistoryDTO toDto(ManagementFirmHistory managementFirmHistory);

    @Mapping(source = "managementFirmFeeDTO", target = "managementFirmFee")
    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    ManagementFirmHistory toEntity(ManagementFirmHistoryDTO managementFirmHistoryDTO);
}
