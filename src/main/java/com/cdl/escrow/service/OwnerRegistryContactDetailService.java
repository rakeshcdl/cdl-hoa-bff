package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryContactDetailDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryContactDetailService {

    Page<OwnerRegistryContactDetailDTO> getAllOwnerRegistryContactDetail(final Pageable pageable);

    Optional<OwnerRegistryContactDetailDTO> getOwnerRegistryContactDetailById(Long id);

    OwnerRegistryContactDetailDTO saveOwnerRegistryContactDetail(OwnerRegistryContactDetailDTO ownerRegistryContactDetailDTO);

    OwnerRegistryContactDetailDTO updateOwnerRegistryContactDetail(Long id, OwnerRegistryContactDetailDTO ownerRegistryContactDetailDTO);

    Boolean deleteOwnerRegistryContactDetailById(Long id);

    boolean softOwnerRegistryContactDetailServiceById(Long id);

    void finalizeOwnerRegistryContactDetail(Long moduleId, TaskStatus status);
}
