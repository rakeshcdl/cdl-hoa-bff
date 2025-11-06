package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmService {
    Page<ManagementFirmDTO> getAllManagementFirm(final Pageable pageable);

    Optional<ManagementFirmDTO> getManagementFirmById(Long id);

    ManagementFirmDTO saveManagementFirm(ManagementFirmDTO managementFirmDTO);

    ManagementFirmDTO updateManagementFirm(Long id, ManagementFirmDTO managementFirmDTO);

    Boolean deleteManagementFirmById(Long id);

    void finalizeManagementFirm(Long moduleId, TaskStatus status);

    boolean softManagementFirmServiceById(Long id);
}
