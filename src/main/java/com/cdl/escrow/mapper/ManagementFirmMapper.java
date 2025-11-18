package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, TaskStatusMapper.class, AssetRegisterMapper.class})
public interface ManagementFirmMapper extends EntityMapper<ManagementFirmDTO, ManagementFirm> {

    @Mapping(source = "mfStatus", target = "mfStatusDTO")
    @Mapping(source = "mfType", target = "mfTypeDTO")
    @Mapping(source = "mfAccountStatus", target = "mfAccountStatusDTO")
    @Mapping(source = "mfConstructionCostCurrency", target = "mfConstructionCostCurrencyDTO")
    @Mapping(source = "assetRegister", target = "assetRegisterDTO")
    @Mapping(source = "mfBlockPaymentTypes", target = "mfBlockPaymentTypeDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    ManagementFirmDTO toDto(ManagementFirm managementFirm);


    @Mapping(source = "mfStatusDTO", target = "mfStatus")
    @Mapping(source = "mfTypeDTO", target = "mfType")
    @Mapping(source = "mfAccountStatusDTO", target = "mfAccountStatus")
    @Mapping(source = "mfConstructionCostCurrencyDTO", target = "mfConstructionCostCurrency")
    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    @Mapping(source = "mfBlockPaymentTypeDTO", target = "mfBlockPaymentTypes")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    ManagementFirm toEntity(ManagementFirmDTO managementFirmDTO);

    default ManagementFirm fromId(Long id) {
        if (id == null) return null;
        ManagementFirm entity = new ManagementFirm();
        entity.setId(id);
        return entity;
    }

}
