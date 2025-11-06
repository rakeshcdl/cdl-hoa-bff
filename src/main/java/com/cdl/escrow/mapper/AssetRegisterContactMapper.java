package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AssetRegisterContactDTO;
import com.cdl.escrow.entity.AssetRegisterContact;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
uses = { AssetRegisterMapper.class })
public interface AssetRegisterContactMapper extends EntityMapper<AssetRegisterContactDTO, AssetRegisterContact> {

    @Override
    @Mapping(source = "assetRegister", target = "assetRegisterDTO")
    AssetRegisterContactDTO toDto(AssetRegisterContact assetRegisterContact);

    @Override
    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    AssetRegisterContact toEntity(AssetRegisterContactDTO assetRegisterContactDTO);
}
