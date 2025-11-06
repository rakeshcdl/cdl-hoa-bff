package com.cdl.escrow.service;

import com.cdl.escrow.dto.OwnerRegistryBankInfoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OwnerRegistryBankInfoService {
    Page<OwnerRegistryBankInfoDTO> getAllOwnerRegistryBankInfo(final Pageable pageable);

    Optional<OwnerRegistryBankInfoDTO> getOwnerRegistryBankInfoById(Long id);

    OwnerRegistryBankInfoDTO saveOwnerRegistryBankInfo(OwnerRegistryBankInfoDTO ownerRegistryBankInfoDTO);

    OwnerRegistryBankInfoDTO updateOwnerRegistryBankInfo(Long id, OwnerRegistryBankInfoDTO ownerRegistryBankInfoDTO);

    Boolean deleteOwnerRegistryBankInfoById(Long id);

    boolean softOwnerRegistryBankInfoServiceById(Long id);
}
