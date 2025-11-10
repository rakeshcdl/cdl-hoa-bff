package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import com.cdl.escrow.entity.ManagementFirmFee;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, ManagementFirmMapper.class })
public interface ManagementFirmFeeMapper extends EntityMapper<ManagementFirmFeeDTO, ManagementFirmFee> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    @Mapping(source = "mffCategory", target = "mffCategoryDTO")
    @Mapping(source = "mffCurrency", target = "mffCurrencyDTO")
    @Mapping(source = "mffFrequency", target = "mffFrequencyDTO")
    @Mapping(source = "mffAccountType", target = "mffAccountTypeDTO")
    ManagementFirmFeeDTO toDto(ManagementFirmFee managementFirmFee);

    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "mffCategoryDTO", target = "mffCategory")
    @Mapping(source = "mffCurrencyDTO", target = "mffCurrency")
    @Mapping(source = "mffAccountTypeDTO", target = "mffAccountType")
    ManagementFirmFee toEntity(ManagementFirmFeeDTO managementFirmFeeDTO);
}
