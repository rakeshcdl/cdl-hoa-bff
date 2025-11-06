package com.cdl.escrow.repository;



import com.cdl.escrow.entity.OwnerRegistryPaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryPaymentPlanRepository extends JpaRepository<OwnerRegistryPaymentPlan,Long>, JpaSpecificationExecutor<OwnerRegistryPaymentPlan> {
   Optional<OwnerRegistryPaymentPlan> findByIdAndDeletedFalse(Long id);
}


