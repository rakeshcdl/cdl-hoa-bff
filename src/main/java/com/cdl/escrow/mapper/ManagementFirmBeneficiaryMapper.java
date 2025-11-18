package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import com.cdl.escrow.entity.ManagementFirmBeneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, ManagementFirmMapper.class})
public interface ManagementFirmBeneficiaryMapper extends EntityMapper<ManagementFirmBeneficiaryDTO, ManagementFirmBeneficiary> {

    @Mapping(source = "mfbTransferType", target = "mfbTransferTypeDTO")
    @Mapping(source = "mfbExpenseType", target = "mfbExpenseTypeDTO")
    @Mapping(source = "mfbVendorSubType", target = "mfbVendorSubTypeDTO")
    @Mapping(source = "mfbContractorSubType", target = "mfbContractorSubTypeDTO")
    @Mapping(source = "mfbInfrastructureCategory", target = "mfbInfrastructureCategoryDTO")
    @Mapping(source = "mfbSalesCategory", target = "mfbSalesCategoryDTO")
    @Mapping(source = "managementFirms", target = "managementFirmDTO")
    ManagementFirmBeneficiaryDTO toDto(ManagementFirmBeneficiary managementFirmBeneficiary);

    @Mapping(source = "mfbTransferTypeDTO", target = "mfbTransferType")
    @Mapping(source = "mfbExpenseTypeDTO", target = "mfbExpenseType")
    @Mapping(source = "mfbVendorSubTypeDTO", target = "mfbVendorSubType")
    @Mapping(source = "mfbContractorSubTypeDTO", target = "mfbContractorSubType")
    @Mapping(source = "mfbInfrastructureCategoryDTO", target = "mfbInfrastructureCategory")
    @Mapping(source = "mfbSalesCategoryDTO", target = "mfbSalesCategory")
    @Mapping(source = "managementFirmDTO", target = "managementFirms")
    ManagementFirmBeneficiary toEntity(ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);



}
