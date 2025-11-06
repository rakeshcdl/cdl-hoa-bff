package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import com.cdl.escrow.entity.ManagementFirmBeneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class})
public interface ManagementFirmBeneficiaryMapper extends EntityMapper<ManagementFirmBeneficiaryDTO, ManagementFirmBeneficiary> {

    @Mapping(source = "reabTranferType", target = "reabTranferTypeDTO")
    @Mapping(source = "reabExpenseType", target = "reabExpenseTypeDTO")
    @Mapping(source = "reabVendorSubType", target = "reabVendorSubTypeDTO")
    @Mapping(source = "reabContractorSubType", target = "reabContractorSubTypeDTO")
    @Mapping(source = "reabInfrastructureCategory", target = "reabInfrastructureCategoryDTO")
    @Mapping(source = "reabSalesCategory", target = "reabSalesCategoryDTO")
    @Mapping(source = "managementFirms", target = "managementFirmDTO")
    ManagementFirmBeneficiaryDTO toDto(ManagementFirmBeneficiary managementFirmBeneficiary);

    @Mapping(source = "reabTranferTypeDTO", target = "reabTranferType")
    @Mapping(source = "reabExpenseTypeDTO", target = "reabExpenseType")
    @Mapping(source = "reabVendorSubTypeDTO", target = "reabVendorSubType")
    @Mapping(source = "reabContractorSubTypeDTO", target = "reabContractorSubType")
    @Mapping(source = "reabInfrastructureCategoryDTO", target = "reabInfrastructureCategory")
    @Mapping(source = "reabSalesCategoryDTO", target = "reabSalesCategory")
    @Mapping(source = "managementFirmDTO", target = "managementFirms")
    ManagementFirmBeneficiary toEntity(ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);



}
