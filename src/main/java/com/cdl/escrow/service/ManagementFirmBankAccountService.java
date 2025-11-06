package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmBankAccountService {
    Page<ManagementFirmBankAccountDTO> getAllManagementFirmBankAccount(final Pageable pageable);

    Optional<ManagementFirmBankAccountDTO> getManagementFirmBankAccountById(Long id);

    ManagementFirmBankAccountDTO saveManagementFirmBankAccount(ManagementFirmBankAccountDTO managementFirmBankAccountDTO);

    ManagementFirmBankAccountDTO updateManagementFirmBankAccount(Long id, ManagementFirmBankAccountDTO managementFirmBankAccountDTO);

    Boolean deleteManagementFirmBankAccountById(Long id);

    boolean softManagementFirmBankAccountServiceById(Long id);
}
