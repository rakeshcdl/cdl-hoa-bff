package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryUnitTypeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryUnitTypeService {
    Page<OwnerRegistryUnitTypeDTO> getAllOwnerRegistryUnitType(final Pageable pageable);

    Optional<OwnerRegistryUnitTypeDTO> getOwnerRegistryUnitTypeById(Long id);

    OwnerRegistryUnitTypeDTO saveOwnerRegistryUnitType(OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO);

    OwnerRegistryUnitTypeDTO updateOwnerRegistryUnitType(Long id, OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO);

    Boolean deleteOwnerRegistryUnitTypeById(Long id);

    boolean softOwnerRegistryUnitTypeServiceById(Long id);
}
