package com.cdl.escrow.service;

import com.cdl.escrow.dto.AssetRegisterDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AssetRegisterService {
    Page<AssetRegisterDTO> getAllAssetRegister(final Pageable pageable);

    Optional<AssetRegisterDTO> getAssetRegisterById(Long id);

    AssetRegisterDTO saveAssetRegister(AssetRegisterDTO assetRegisterDTO);

    AssetRegisterDTO updateAssetRegister(Long id, AssetRegisterDTO assetRegisterDTO);

    Boolean deleteAssetRegisterById(Long id);

    void finalizeAssetRegister(Long moduleId, TaskStatus status);

    boolean softAssetRegisterContactServiceById(Long id);

    //Optional<AssetRegisterRecord> getAssetRegisterDataById(Long id);
}
