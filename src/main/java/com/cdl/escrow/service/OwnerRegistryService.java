package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryService {
    Page<OwnerRegistryDTO> getAllOwnerRegistry(final Pageable pageable);

    Optional<OwnerRegistryDTO> getOwnerRegistryById(Long id);

    OwnerRegistryDTO saveOwnerRegistry(OwnerRegistryDTO ownerRegistryDTO);

    OwnerRegistryDTO updateOwnerRegistry(Long id, OwnerRegistryDTO ownerRegistryDTO);

    Boolean deleteOwnerRegistryById(Long id);

    boolean softOwnerRegistryServiceById(Long id);

    void finalizeOwnerRegistry(Long moduleId, TaskStatus status);
}
