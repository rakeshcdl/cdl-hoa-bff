package com.cdl.escrow.repository;

import com.cdl.escrow.entity.OwnerRegistryContactDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface OwnerRegistryContactDetailRepository extends JpaRepository<OwnerRegistryContactDetail,Long>, JpaSpecificationExecutor<OwnerRegistryContactDetail> {
    Optional<OwnerRegistryContactDetail> findByIdAndDeletedFalse(Long id);
}
