package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import com.cdl.escrow.entity.ManagementFirmFee;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, ManagementFirmMapper.class })
public interface ManagementFirmFeeMapper extends EntityMapper<ManagementFirmFeeDTO, ManagementFirmFee> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    @Mapping(source = "reafCategory", target = "reafCategoryDTO")
    @Mapping(source = "reafCurrency", target = "reafCurrencyDTO")
    @Mapping(source = "reafFrequency", target = "reafFrequencyDTO")
    @Mapping(source = "reafAccountType", target = "reafAccountTypeDTO")
    ManagementFirmFeeDTO toDto(ManagementFirmFee managementFirmFee);

    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "reafCategoryDTO", target = "reafCategory")
    @Mapping(source = "reafCurrencyDTO", target = "reafCurrency")
    @Mapping(source = "reafAccountTypeDTO", target = "reafAccountType")
    ManagementFirmFee toEntity(ManagementFirmFeeDTO managementFirmFeeDTO);
}
