package com.cdl.escrow.repository;


import com.cdl.escrow.entity.ManagementFirm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmRepository extends JpaRepository<ManagementFirm,Long> , JpaSpecificationExecutor<ManagementFirm> {
    Optional<ManagementFirm> findByIdAndDeletedFalse(Long id);
}
