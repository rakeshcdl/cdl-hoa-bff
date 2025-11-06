package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ManagementFirmPaymentPlanService {

    Page<ManagementFirmPaymentPlanDTO> getAllManagementFirmPaymentPlan(final Pageable pageable);

    Optional<ManagementFirmPaymentPlanDTO> getManagementFirmPaymentPlanById(Long id);

    ManagementFirmPaymentPlanDTO saveManagementFirmPaymentPlan(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO);

    ManagementFirmPaymentPlanDTO updateManagementFirmPaymentPlan(Long id, ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO);

    Boolean deleteManagementFirmPaymentPlanById(Long id);

    ManagementFirmPaymentPlanDTO saveAllManagementFirmPaymentPlan(@Valid List<ManagementFirmPaymentPlanDTO> dto);

    boolean softManagementFirmPaymentPlanServiceById(Long id);
}
