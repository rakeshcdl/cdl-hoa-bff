package com.cdl.escrow.repository;

import com.cdl.escrow.entity.ManagementFirmBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagementFirmBankAccountRepository extends JpaRepository<ManagementFirmBankAccount,Long> , JpaSpecificationExecutor<ManagementFirmBankAccount> {
    Optional<ManagementFirmBankAccount> findByIdAndDeletedFalse(Long id);
}
