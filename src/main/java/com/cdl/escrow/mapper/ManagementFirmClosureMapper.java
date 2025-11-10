package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import com.cdl.escrow.entity.ManagementFirmClosure;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class , ManagementFirmMapper.class, RealEstateDocumentMapper.class})
public interface ManagementFirmClosureMapper extends EntityMapper<ManagementFirmClosureDTO, ManagementFirmClosure> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    @Mapping(source = "mfcDocument", target = "mfcDocumentDTO")
    ManagementFirmClosureDTO toDto(ManagementFirmClosure managementFirmClosure);

    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "mfcDocumentDTO", target = "mfcDocument")
    ManagementFirmClosure toEntity(ManagementFirmClosureDTO managementFirmClosureDTO);
}
