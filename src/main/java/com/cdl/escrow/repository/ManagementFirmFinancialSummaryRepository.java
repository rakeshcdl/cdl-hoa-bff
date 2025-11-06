package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ManagementFirmFinancialSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmFinancialSummaryRepository extends JpaRepository<ManagementFirmFinancialSummary,Long> , JpaSpecificationExecutor<ManagementFirmFinancialSummary> {
    Optional<ManagementFirmFinancialSummary> findByIdAndDeletedFalse(Long id);
}
