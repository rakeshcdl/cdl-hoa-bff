package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ManagementFirmPaymentPlanService {

    Page<ManagementFirmPaymentPlanDTO> getAllRealEstateAssestPaymentPlan(final Pageable pageable);

    Optional<ManagementFirmPaymentPlanDTO> getRealEstateAssestPaymentPlanById(Long id);

    ManagementFirmPaymentPlanDTO saveRealEstateAssestPaymentPlan(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO);

    ManagementFirmPaymentPlanDTO updateRealEstateAssestPaymentPlan(Long id, ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO);

    Boolean deleteRealEstateAssestPaymentPlanById(Long id);

    ManagementFirmPaymentPlanDTO saveAllRealEstateAssestPaymentPlan(@Valid List<ManagementFirmPaymentPlanDTO> dto);

    boolean softRealEstateAssestPaymentPlanServiceById(Long id);
}
