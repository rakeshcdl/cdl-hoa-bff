package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryPaymentPlanDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface OwnerRegistryPaymentPlanService {

    Page<OwnerRegistryPaymentPlanDTO> getAllOwnerRegistryPaymentPlan(final Pageable pageable);

    Optional<OwnerRegistryPaymentPlanDTO> getROwnerRegistryPaymentPlanById(Long id);

    OwnerRegistryPaymentPlanDTO saveOwnerRegistryPaymentPlan(OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO);

    OwnerRegistryPaymentPlanDTO updateOwnerRegistryPaymentPlan(Long id, OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO);

    Boolean deleteOwnerRegistryPaymentPlanById(Long id);

    OwnerRegistryPaymentPlanDTO saveAllOwnerRegistryPaymentPlan(@Valid List<OwnerRegistryPaymentPlanDTO> ownerRegistryPaymentPlanDTOS);

    boolean softOwnerRegistryPaymentPlanServiceById(Long id);
}
