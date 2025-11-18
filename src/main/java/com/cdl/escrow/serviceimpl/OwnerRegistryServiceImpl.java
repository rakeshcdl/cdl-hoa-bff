package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.entity.OwnerRegistry;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryMapper;
import com.cdl.escrow.repository.OwnerRegistryRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.OwnerRegistryService;
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
public class OwnerRegistryServiceImpl implements OwnerRegistryService {
    private final OwnerRegistryRepository repository;

    private final OwnerRegistryMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryDTO> getAllOwnerRegistry(Pageable pageable) {
        log.debug("Fetching all Owner Registry, page: {}", pageable.getPageNumber());
        Page<OwnerRegistry> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryDTO> getOwnerRegistryById(Long id) {
        log.debug("Fetching Owner Registry with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryDTO saveOwnerRegistry(OwnerRegistryDTO ownerRegistryDTO) {
        log.info("Saving new Owner Registry");
        OwnerRegistry entity = mapper.toEntity(ownerRegistryDTO);
        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);
        OwnerRegistry saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public OwnerRegistryDTO updateOwnerRegistry(Long id, OwnerRegistryDTO ownerRegistryDTO) {
        log.info("Updating Owner Registry with ID: {}", id);

        OwnerRegistry existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Capital partner not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistry toUpdate = mapper.toEntity(ownerRegistryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved
        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);
        OwnerRegistry updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteOwnerRegistryById(Long id) {
        log.info("Deleting Owner Registry  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Capital partner not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryServiceById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException.ResourceNotFoundException("Entity not found: " + id));

        if (Boolean.TRUE.equals(entity.getDeleted())) {
            throw new ApplicationConfigurationNotFoundException.ConflictException("Entity already deleted: " + id);
        }

        entity.setDeleted(true);
        repository.save(entity);
        return true;
    }

    @Override
    @Transactional
    public void finalizeOwnerRegistry(Long moduleId, TaskStatus status) {

        OwnerRegistry ownerRegistry =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Capital Partner not found: " + moduleId));

        ownerRegistry.setTaskStatus(status);
        repository.save(ownerRegistry);
    }
}
