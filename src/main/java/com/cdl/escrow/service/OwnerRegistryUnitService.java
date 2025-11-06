package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryUnitService {
    Page<OwnerRegistryUnitDTO> getAllOwnerRegistryUnit(final Pageable pageable);

    Optional<OwnerRegistryUnitDTO> getOwnerRegistryUnitById(Long id);

    OwnerRegistryUnitDTO saveOwnerRegistryUnit(OwnerRegistryUnitDTO ownerRegistryUnitDTO);

    OwnerRegistryUnitDTO updateOwnerRegistryUnit(Long id, OwnerRegistryUnitDTO ownerRegistryUnitDTO);

    Boolean deleteOwnerRegistryUnitById(Long id);

    boolean softOwnerRegistryUnitServiceById(Long id);
}
