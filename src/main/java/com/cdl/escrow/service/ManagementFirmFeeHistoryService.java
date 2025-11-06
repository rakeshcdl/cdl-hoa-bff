package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFeeHistoryService {
    Page<ManagementFirmHistoryDTO> getAllManagementFirmFeeHistory(final Pageable pageable);

    Optional<ManagementFirmHistoryDTO> getManagementFirmFeeHistoryById(Long id);

    ManagementFirmHistoryDTO saveManagementFirmFeeHistory(ManagementFirmHistoryDTO managementFirmHistoryDTO);

    ManagementFirmHistoryDTO updateManagementFirmFeeHistory(Long id, ManagementFirmHistoryDTO managementFirmHistoryDTO);

    Boolean deleteManagementFirmFeeHistoryById(Long id);

    boolean softManagementFirmFeeHistoryServiceById(Long id);
}
