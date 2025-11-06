package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmService {
    Page<ManagementFirmDTO> getAllRealEstateAssest(final Pageable pageable);

    Optional<ManagementFirmDTO> getRealEstateAssestById(Long id);

    ManagementFirmDTO saveRealEstateAssest(ManagementFirmDTO managementFirmDTO);

    ManagementFirmDTO updateRealEstateAssest(Long id, ManagementFirmDTO managementFirmDTO);

    Boolean deleteRealEstateAssestById(Long id);

    void finalizeRealEstateAssest(Long moduleId, TaskStatus status);

    boolean softRealEstateAssestServiceById(Long id);
}
