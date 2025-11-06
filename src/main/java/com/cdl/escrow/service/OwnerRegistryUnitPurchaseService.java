package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryUnitPurchaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryUnitPurchaseService {
    Page<OwnerRegistryUnitPurchaseDTO> getAllOwnerRegistryUnitPurchase(final Pageable pageable);

    Optional<OwnerRegistryUnitPurchaseDTO> getOwnerRegistryUnitPurchaseById(Long id);

    OwnerRegistryUnitPurchaseDTO saveOwnerRegistryUnitPurchase(OwnerRegistryUnitPurchaseDTO ownerRegistryUnitPurchaseDTO);

    OwnerRegistryUnitPurchaseDTO updateOwnerRegistryUnitPurchase(Long id, OwnerRegistryUnitPurchaseDTO ownerRegistryUnitPurchaseDTO);

    Boolean deleteOwnerRegistryUnitPurchaseById(Long id);

    boolean softOwnerRegistryUnitPurchaseServiceById(Long id);
}
