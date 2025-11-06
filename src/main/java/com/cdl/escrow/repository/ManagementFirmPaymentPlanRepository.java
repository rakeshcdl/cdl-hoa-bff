package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ManagementFirmPaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ManagementFirmPaymentPlanRepository extends JpaRepository<ManagementFirmPaymentPlan,Long>, JpaSpecificationExecutor<ManagementFirmPaymentPlan> {
    Optional<ManagementFirmPaymentPlan> findByIdAndDeletedFalse(Long id);
}