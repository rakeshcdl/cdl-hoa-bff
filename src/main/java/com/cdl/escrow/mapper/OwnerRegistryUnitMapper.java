package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import com.cdl.escrow.entity.OwnerRegistryUnit;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {ApplicationSettingMapper.class, OwnerRegistryMapper.class, ManagementFirmMapper.class,
OwnerRegistryUnitBookingMapper.class})
public interface OwnerRegistryUnitMapper extends EntityMapper<OwnerRegistryUnitDTO, OwnerRegistryUnit> {

    OwnerRegistryUnitMapper INSTANCE = Mappers.getMapper(OwnerRegistryUnitMapper.class);

    @Mapping(source = "partnerUnit", target = "partnerUnitDTO")
    @Mapping(source = "ownerRegistryUnitType", target = "ownerRegistryUnitTypeDTO")
    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    @Mapping(source = "unitStatus", target = "unitStatusDTO")
    @Mapping(source = "propertyId", target = "propertyIdDTO")
    @Mapping(source = "paymentPlanType", target = "paymentPlanTypeDTO")
    @Mapping(source = "ownerRegistryUnitBooking", target = "ownerRegistryUnitBookingDTO")
    OwnerRegistryUnitDTO toDto(OwnerRegistryUnit ownerRegistryUnit);

    @Mapping(source = "partnerUnitDTO", target = "partnerUnit")
    @Mapping(source = "ownerRegistryUnitTypeDTO", target = "ownerRegistryUnitType")
    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "unitStatusDTO", target = "unitStatus")
    @Mapping(source = "propertyIdDTO", target = "propertyId")
    @Mapping(source = "paymentPlanTypeDTO", target = "paymentPlanType")
    @Mapping(source = "ownerRegistryUnitBookingDTO", target = "ownerRegistryUnitBooking")
    OwnerRegistryUnit toEntity(OwnerRegistryUnitDTO ownerRegistryUnitDTO);
}
