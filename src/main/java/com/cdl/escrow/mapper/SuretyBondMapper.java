package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.SuretyBondDTO;
import com.cdl.escrow.entity.SuretyBond;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, ManagementFirmMapper.class})
public interface SuretyBondMapper  extends EntityMapper<SuretyBondDTO, SuretyBond> {

    @Mapping(source = "suretyBondType", target = "suretyBondTypeDTO")
    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    @Mapping(source = "issuerBank", target = "issuerBankDTO")
    @Mapping(source = "suretyBondStatus", target = "suretyBondStatusDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    SuretyBondDTO toDto(SuretyBond suretyBond);

    @Mapping(source = "suretyBondTypeDTO", target = "suretyBondType")
    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "issuerBankDTO", target = "issuerBank")
    @Mapping(source = "suretyBondStatusDTO", target = "suretyBondStatus")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    SuretyBond toEntity(SuretyBondDTO suretyBondDTO);
}
