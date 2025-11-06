package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmClosureService {
    Page<ManagementFirmClosureDTO> getAllManagementFirmClosure(final Pageable pageable);

    Optional<ManagementFirmClosureDTO> getManagementFirmClosureById(Long id);

    ManagementFirmClosureDTO saveManagementFirmClosure(ManagementFirmClosureDTO managementFirmClosureDTO);

    ManagementFirmClosureDTO updateManagementFirmClosure(Long id, ManagementFirmClosureDTO managementFirmClosureDTO);

    Boolean deleteManagementFirmClosureById(Long id);

    boolean softManagementFirmClosureServiceById(Long id);
}
