package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryUnitBookingDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryUnitBookingService {
    Page<OwnerRegistryUnitBookingDTO> getAllOwnerRegistryUnitBooking(final Pageable pageable);

    Optional<OwnerRegistryUnitBookingDTO> getOwnerRegistryUnitBookingById(Long id);

    OwnerRegistryUnitBookingDTO saveOwnerRegistryUnitBooking(OwnerRegistryUnitBookingDTO ownerRegistryUnitBookingDTO);

    OwnerRegistryUnitBookingDTO updateOwnerRegistryUnitBooking(Long id, OwnerRegistryUnitBookingDTO ownerRegistryUnitBookingDTO);

    Boolean deleteOwnerRegistryUnitBookingById(Long id);

    boolean softOwnerRegistryUnitBookingServiceById(Long id);
}
