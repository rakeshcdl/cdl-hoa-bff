package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.AssetRegisterDTO;
import com.cdl.escrow.entity.AssetRegister;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , uses = {ApplicationSettingMapper.class, TaskStatusMapper.class})
public interface AssetRegisterMapper extends EntityMapper<AssetRegisterDTO, AssetRegister> {

    AssetRegisterMapper INSTANCE = Mappers.getMapper(AssetRegisterMapper.class);

    @Mapping(source = "arRegulator", target = "arRegulatorDTO")
    @Mapping(source = "arActiveStatus", target = "arActiveStatusDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    AssetRegisterDTO toDto(AssetRegister assetRegister);

    @Mapping(source = "arRegulatorDTO", target = "arRegulator")
    @Mapping(source = "arActiveStatusDTO", target = "arActiveStatus")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    AssetRegister toEntity(AssetRegisterDTO assetRegisterDTO);

}
