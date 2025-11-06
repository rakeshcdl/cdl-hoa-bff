package com.cdl.escrow.service;

import com.cdl.escrow.dto.AssetRegisterContactDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AssetRegisterContactService {
    Page<AssetRegisterContactDTO> getAllAssetRegisterContact(final Pageable pageable);

    Optional<AssetRegisterContactDTO> getAssetRegisterContactById(Long id);

    AssetRegisterContactDTO saveAssetRegisterContact(AssetRegisterContactDTO assetRegisterContactDTO);

    AssetRegisterContactDTO updateAssetRegisterContact(Long id, AssetRegisterContactDTO assetRegisterContactDTO);

    Boolean deleteAssetRegisterContactById(Long id);

    boolean softAssetRegisterContactServiceById(Long id);
}
