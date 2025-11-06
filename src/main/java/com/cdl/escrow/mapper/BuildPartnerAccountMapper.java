package com.cdl.escrow.mapper;


import com.cdl.escrow.dto.BuildPartnerAccountDTO;
import com.cdl.escrow.entity.BuildPartnerAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BuildPartnerAccountMapper extends EntityMapper<BuildPartnerAccountDTO, BuildPartnerAccount> {

    @Mapping(source = "assetRegister", target = "assetRegisterDTO")
    BuildPartnerAccountDTO toDto(BuildPartnerAccount buildPartnerAccount);


    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    BuildPartnerAccount toEntity(BuildPartnerAccountDTO buildPartnerAccountDTO);
}
