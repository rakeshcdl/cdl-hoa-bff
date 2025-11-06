package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class})
public interface ManagementFirmMapper extends EntityMapper<ManagementFirmDTO, ManagementFirm> {

    @Mapping(source = "reaStatus", target = "reaStatusDTO")
    @Mapping(source = "reaType", target = "reaTypeDTO")
    @Mapping(source = "reaAccountStatus", target = "reaAccountStatusDTO")
    @Mapping(source = "reaConstructionCostCurrency", target = "reaConstructionCostCurrencyDTO")
    @Mapping(source = "assetRegister", target = "assetRegisterDTO")
    @Mapping(source = "reaBlockPaymentTypes", target = "reaBlockPaymentTypeDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    ManagementFirmDTO toDto(ManagementFirm managementFirm);


    @Mapping(source = "reaStatusDTO", target = "reaStatus")
    @Mapping(source = "reaTypeDTO", target = "reaType")
    @Mapping(source = "reaAccountStatusDTO", target = "reaAccountStatus")
    @Mapping(source = "reaConstructionCostCurrencyDTO", target = "reaConstructionCostCurrency")
    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    @Mapping(source = "reaBlockPaymentTypeDTO", target = "reaBlockPaymentTypes")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    ManagementFirm toEntity(ManagementFirmDTO managementFirmDTO);

    default ManagementFirm fromId(Long id) {
        if (id == null) return null;
        ManagementFirm entity = new ManagementFirm();
        entity.setId(id);
        return entity;
    }

}
