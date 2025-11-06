package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFeeHistoryService {
    Page<ManagementFirmHistoryDTO> getAllRealEstateAssestFeeHistory(final Pageable pageable);

    Optional<ManagementFirmHistoryDTO> getRealEstateAssestFeeHistoryById(Long id);

    ManagementFirmHistoryDTO saveRealEstateAssestFeeHistory(ManagementFirmHistoryDTO managementFirmHistoryDTO);

    ManagementFirmHistoryDTO updateRealEstateAssestFeeHistory(Long id, ManagementFirmHistoryDTO managementFirmHistoryDTO);

    Boolean deleteRealEstateAssestFeeHistoryById(Long id);

    boolean softRealEstateAssestFeeHistoryServiceById(Long id);
}
