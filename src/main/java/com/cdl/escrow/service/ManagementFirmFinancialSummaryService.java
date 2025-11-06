package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFinancialSummaryService {
    Page<ManagementFirmFinancialSummaryDTO> getAllRealEstateAssestFinancialSummary(final Pageable pageable);

    Optional<ManagementFirmFinancialSummaryDTO> getRealEstateAssestFinancialSummaryById(Long id);

    ManagementFirmFinancialSummaryDTO saveRealEstateAssestFinancialSummary(ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO);

    ManagementFirmFinancialSummaryDTO updateRealEstateAssestFinancialSummary(Long id, ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO);

    Boolean deleteRealEstateAssestFinancialSummaryById(Long id);

    boolean softRealEstateAssestFinancialSummaryServiceById(Long id);
}
