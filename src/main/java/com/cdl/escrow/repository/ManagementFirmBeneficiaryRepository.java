package com.cdl.escrow.repository;


import com.cdl.escrow.entity.ManagementFirmBeneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmBeneficiaryRepository extends JpaRepository<ManagementFirmBeneficiary,Long> , JpaSpecificationExecutor<ManagementFirmBeneficiary> {
    Optional<ManagementFirmBeneficiary> findByIdAndDeletedFalse(Long id);
}
