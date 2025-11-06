package com.cdl.escrow.repository;


import com.cdl.escrow.entity.OwnerRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryRepository extends JpaRepository<OwnerRegistry,Long> , JpaSpecificationExecutor<OwnerRegistry> {
    Optional<OwnerRegistry> findByIdAndDeletedFalse(Long id);
}
