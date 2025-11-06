package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmBeneficiaryService {
    Page<ManagementFirmBeneficiaryDTO> getAllManagementFirmBeneficiary(final Pageable pageable);

    Optional<ManagementFirmBeneficiaryDTO> getManagementFirmBeneficiaryById(Long id);

    ManagementFirmBeneficiaryDTO saveManagementFirmBeneficiary(ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);

    ManagementFirmBeneficiaryDTO updateManagementFirmBeneficiary(Long id, ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);

    Boolean deleteManagementFirmBeneficiaryById(Long id);

    boolean softManagementFirmBeneficiaryServiceById(Long id);
}
