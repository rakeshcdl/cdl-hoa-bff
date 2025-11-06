package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ManagementFirmClosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmClosureRepository extends JpaRepository<ManagementFirmClosure,Long> , JpaSpecificationExecutor<ManagementFirmClosure> {
    Optional<ManagementFirmClosure> findByIdAndDeletedFalse(Long id);
}
