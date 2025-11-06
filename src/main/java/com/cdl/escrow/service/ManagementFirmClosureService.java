package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmClosureService {
    Page<ManagementFirmClosureDTO> getAllRealEstateAssestClosure(final Pageable pageable);

    Optional<ManagementFirmClosureDTO> getRealEstateAssestClosureById(Long id);

    ManagementFirmClosureDTO saveRealEstateAssestClosure(ManagementFirmClosureDTO managementFirmClosureDTO);

    ManagementFirmClosureDTO updateRealEstateAssestClosure(Long id, ManagementFirmClosureDTO managementFirmClosureDTO);

    Boolean deleteRealEstateAssestClosureById(Long id);

    boolean softRealEstateAssestClosureServiceById(Long id);
}
