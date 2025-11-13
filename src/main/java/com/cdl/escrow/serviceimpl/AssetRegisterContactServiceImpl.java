package com.cdl.escrow.serviceimpl;


import com.cdl.escrow.dto.AssetRegisterContactDTO;
import com.cdl.escrow.entity.AssetRegisterContact;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AssetRegisterContactMapper;
import com.cdl.escrow.repository.AssetRegisterContactRepository;
import com.cdl.escrow.service.AssetRegisterContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssetRegisterContactServiceImpl implements AssetRegisterContactService {

    private final AssetRegisterContactRepository repository;

    private final AssetRegisterContactMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<AssetRegisterContactDTO> getAllAssetRegisterContact(Pageable pageable) {
        log.debug("Fetching all Asset Register contact, page: {}", pageable.getPageNumber());
        Page<AssetRegisterContact> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AssetRegisterContactDTO> getAssetRegisterContactById(Long id) {
        log.debug("Fetching Asset Register contact with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public AssetRegisterContactDTO saveAssetRegisterContact(AssetRegisterContactDTO assetRegisterContactDTO) {
        log.info("Saving new Asset Register contact");
        AssetRegisterContact entity = mapper.toEntity(assetRegisterContactDTO);
        AssetRegisterContact saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public AssetRegisterContactDTO updateAssetRegisterContact(Long id, AssetRegisterContactDTO assetRegisterContactDTO) {
        log.info("Updating Asset Register contact with ID: {}", id);

        AssetRegisterContact existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Build partner contact not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AssetRegisterContact toUpdate = mapper.toEntity(assetRegisterContactDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        AssetRegisterContact updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteAssetRegisterContactById(Long id) {
        log.info("Deleting Asset Register contact with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Build partner contact not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softAssetRegisterContactServiceById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException.ResourceNotFoundException("Entity not found: " + id));

        if (Boolean.TRUE.equals(entity.getDeleted())) {
            throw new ApplicationConfigurationNotFoundException.ConflictException("Entity already deleted: " + id);
        }

        entity.setDeleted(true);
        repository.save(entity);
        return true;
    }
}
