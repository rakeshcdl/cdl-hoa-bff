package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BuildPartnerBeneficiaryDTO;
import com.cdl.escrow.entity.BuildPartnerBeneficiary;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BuildPartnerBeneficiaryMapper extends EntityMapper<BuildPartnerBeneficiaryDTO, BuildPartnerBeneficiary> {

    @Mapping(source = "bpbTransferType", target = "bpbTransferTypeDTO")
    @Mapping(source = "assetRegisters" , target = "assetRegisterDTO")
    BuildPartnerBeneficiaryDTO toDto(BuildPartnerBeneficiary buildPartnerBeneficiary);

    @Mapping(source = "bpbTransferTypeDTO", target = "bpbTransferType")
    @Mapping( source = "assetRegisterDTO" , target = "assetRegisters")
    BuildPartnerBeneficiary toEntity(BuildPartnerBeneficiaryDTO buildPartnerBeneficiaryDTO);



}
