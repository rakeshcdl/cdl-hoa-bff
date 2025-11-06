package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFeeService {
    Page<ManagementFirmFeeDTO> getAllRealEstateAssestFee(final Pageable pageable);

    Optional<ManagementFirmFeeDTO> getRealEstateAssestFeeById(Long id);

    ManagementFirmFeeDTO saveRealEstateAssestFee(ManagementFirmFeeDTO managementFirmFeeDTO);

    ManagementFirmFeeDTO updateRealEstateAssestFee(Long id, ManagementFirmFeeDTO managementFirmFeeDTO);

    Boolean deleteRealEstateAssestFeeById(Long id);

    boolean softRealEstateAssestFeeServiceById(Long id);
}
