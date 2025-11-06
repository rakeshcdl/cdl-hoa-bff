package com.cdl.escrow.repository;


import com.cdl.escrow.entity.OwnerRegistryUnitPurchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryUnitPurchaseRepository extends JpaRepository<OwnerRegistryUnitPurchase,Long> , JpaSpecificationExecutor<OwnerRegistryUnitPurchase> {
    Optional<OwnerRegistryUnitPurchase> findByIdAndDeletedFalse(Long id);
}
