package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryUnitBookingDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitBooking;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerRegistryUnitBookingMapper extends EntityMapper<OwnerRegistryUnitBookingDTO, OwnerRegistryUnitBooking> {

    //@Mapping(source = "ownerRegistry", target = "capitalPartnerDTO")
    OwnerRegistryUnitBookingDTO toDto(OwnerRegistryUnitBooking ownerRegistryUnitBooking);

   // @Mapping(source = "ownerRegistry", target = "capitalPartnerDTO")
    OwnerRegistryUnitBooking toEntity(OwnerRegistryUnitBookingDTO ownerRegistryUnitBookingDTO);
}
