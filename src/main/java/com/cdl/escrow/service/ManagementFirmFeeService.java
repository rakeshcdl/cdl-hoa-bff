package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmFeeService {
    Page<ManagementFirmFeeDTO> getAllManagementFirmFee(final Pageable pageable);

    Optional<ManagementFirmFeeDTO> getManagementFirmFeeById(Long id);

    ManagementFirmFeeDTO saveManagementFirmFee(ManagementFirmFeeDTO managementFirmFeeDTO);

    ManagementFirmFeeDTO updateManagementFirmFee(Long id, ManagementFirmFeeDTO managementFirmFeeDTO);

    Boolean deleteManagementFirmFeeById(Long id);

    boolean softManagementFirmFeeServiceById(Long id);
}
