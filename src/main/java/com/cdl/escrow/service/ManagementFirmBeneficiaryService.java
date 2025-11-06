package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmBeneficiaryService {
    Page<ManagementFirmBeneficiaryDTO> getAllRealEstateAssestBeneficiary(final Pageable pageable);

    Optional<ManagementFirmBeneficiaryDTO> getRealEstateAssestBeneficiaryById(Long id);

    ManagementFirmBeneficiaryDTO saveRealEstateAssestBeneficiary(ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);

    ManagementFirmBeneficiaryDTO updateRealEstateAssestBeneficiary(Long id, ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO);

    Boolean deleteRealEstateAssestBeneficiaryById(Long id);

    boolean softRealEstateAssestBeneficiaryServiceById(Long id);
}
