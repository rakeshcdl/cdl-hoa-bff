package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ManagementFirmFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmFeeRepository extends JpaRepository<ManagementFirmFee,Long> , JpaSpecificationExecutor<ManagementFirmFee> {
    Optional<ManagementFirmFee> findByIdAndDeletedFalse(Long id);
}
