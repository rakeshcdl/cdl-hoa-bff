package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFinancialSummaryService {
    Page<ManagementFirmFinancialSummaryDTO> getAllManagementFirmFinancialSummary(final Pageable pageable);

    Optional<ManagementFirmFinancialSummaryDTO> getManagementFirmFinancialSummaryById(Long id);

    ManagementFirmFinancialSummaryDTO saveManagementFirmFinancialSummary(ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO);

    ManagementFirmFinancialSummaryDTO updateManagementFirmFinancialSummary(Long id, ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO);

    Boolean deleteManagementFirmFinancialSummaryById(Long id);

    boolean softManagementFirmFinancialSummaryServiceById(Long id);
}
