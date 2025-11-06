package com.cdl.escrow.repository;


import com.cdl.escrow.entity.ManagementFirmHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmFeeHistoryRepository extends JpaRepository<ManagementFirmHistory,Long> , JpaSpecificationExecutor<ManagementFirmHistory> {
    Optional<ManagementFirmHistory> findByIdAndDeletedFalse(Long id);
}
