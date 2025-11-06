package com.cdl.escrow.repository;


import com.cdl.escrow.entity.OwnerRegistryUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryUnitTypeRepository extends JpaRepository<OwnerRegistryUnitType,Long> , JpaSpecificationExecutor<OwnerRegistryUnitType> {
    Optional<OwnerRegistryUnitType> findByIdAndDeletedFalse(Long id);
}
