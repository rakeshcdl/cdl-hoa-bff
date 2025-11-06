package com.cdl.escrow.repository;

import com.cdl.escrow.entity.OwnerRegistryBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OwnerRegistryBankInfoRepository extends JpaRepository<OwnerRegistryBankInfo,Long> , JpaSpecificationExecutor<OwnerRegistryBankInfo> {
    Optional<OwnerRegistryBankInfo> findByIdAndDeletedFalse(Long id);
}
