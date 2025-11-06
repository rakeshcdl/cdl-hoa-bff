package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryBankInfoDTO;
import com.cdl.escrow.entity.OwnerRegistryBankInfo;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, OwnerRegistryMapper.class })
public interface OwnerRegistryBankInfoMapper extends EntityMapper<OwnerRegistryBankInfoDTO, OwnerRegistryBankInfo> {


    @Mapping(source = "ownerRegistry", target = "ownerRegistryDTO")
    @Mapping(source = "payMode", target = "payModeDTO")
    OwnerRegistryBankInfoDTO toDto(OwnerRegistryBankInfo ownerRegistryBankInfo);


    @Mapping(source = "ownerRegistryDTO", target = "ownerRegistry")
    @Mapping(source = "payModeDTO", target = "payMode")
    OwnerRegistryBankInfo toEntity(OwnerRegistryBankInfoDTO ownerRegistryBankInfoDTO);
}
