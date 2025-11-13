package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.AssetRegisterDTO;
import com.cdl.escrow.entity.AssetRegister;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.AssetRegisterMapper;
import com.cdl.escrow.repository.AssetRegisterRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.AssetRegisterService;
import jakarta.persistence.EntityNotFoundException;
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
public class AssetRegisterServiceImpl implements AssetRegisterService {

   private final AssetRegisterRepository repository;

    private final AssetRegisterMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<AssetRegisterDTO> getAllAssetRegister(Pageable pageable) {
        log.debug("Fetching all build partner, page: {}", pageable.getPageNumber());
        Page<AssetRegister> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AssetRegisterDTO> getAssetRegisterById(Long id) {
        log.debug("Fetching build partner with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public AssetRegisterDTO saveAssetRegister(AssetRegisterDTO assetRegisterDTO) {
        log.info("Saving new build partner");

        AssetRegister entity = mapper.toEntity(assetRegisterDTO);

        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
         entity.setEnabled(true);

        AssetRegister saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public AssetRegisterDTO updateAssetRegister(Long id, AssetRegisterDTO assetRegisterDTO) {
        log.info("Updating build partner with ID: {}", id);

        AssetRegister existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Build partner not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        AssetRegister toUpdate = mapper.toEntity(assetRegisterDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);
        AssetRegister updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteAssetRegisterById(Long id) {
        log.info("Deleting build partner  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Build partner not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void finalizeAssetRegister(Long moduleId, TaskStatus status) {

            AssetRegister partner =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Build Partner not found: " + moduleId));

        partner.setTaskStatus(status);
        repository.save(partner);

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

