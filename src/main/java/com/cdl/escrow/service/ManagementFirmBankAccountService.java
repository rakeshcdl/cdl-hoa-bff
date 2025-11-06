package com.cdl.escrow.service;

import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ManagementFirmBankAccountService {
    Page<ManagementFirmBankAccountDTO> getAllRealEstateBankAccount(final Pageable pageable);

    Optional<ManagementFirmBankAccountDTO> getRealEstateBankAccountById(Long id);

    ManagementFirmBankAccountDTO saveRealEstateBankAccount(ManagementFirmBankAccountDTO managementFirmBankAccountDTO);

    ManagementFirmBankAccountDTO updateRealEstateBankAccount(Long id, ManagementFirmBankAccountDTO managementFirmBankAccountDTO);

    Boolean deleteRealEstateBankAccountById(Long id);

    boolean softRealEstateBankAccountServiceById(Long id);
}
