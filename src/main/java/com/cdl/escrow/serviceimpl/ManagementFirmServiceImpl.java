package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmMapper;
import com.cdl.escrow.repository.ManagementFirmRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.ManagementFirmService;
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
public class ManagementFirmServiceImpl implements ManagementFirmService {

    private final ManagementFirmRepository repository;

    private final ManagementFirmMapper mapper;

    private final TaskStatusRepository taskStatusRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmDTO> getAllRealEstateAssest(Pageable pageable) {
        log.debug("Fetching all Real Estate Assest , page: {}", pageable.getPageNumber());
        Page<ManagementFirm> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmDTO> getRealEstateAssestById(Long id) {
        log.debug("Fetching Real Estate Assest with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmDTO saveRealEstateAssest(ManagementFirmDTO managementFirmDTO) {
        log.info("Saving new Real Estate Assest");
        ManagementFirm entity = mapper.toEntity(managementFirmDTO);

        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);

        ManagementFirm saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmDTO updateRealEstateAssest(Long id, ManagementFirmDTO managementFirmDTO) {
        log.info("Updating Real EstateAssest with ID: {}", id);

        ManagementFirm existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Real EstateAssest  not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirm toUpdate = mapper.toEntity(managementFirmDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);

        ManagementFirm updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteRealEstateAssestById(Long id) {
        log.info("Deleting Real EstateAssest  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public void finalizeRealEstateAssest(Long moduleId, TaskStatus status) {


        ManagementFirm assest =  repository.findById(moduleId)
                .orElseThrow(() -> new IllegalStateException("Build Partner Assets not found: " + moduleId));

        assest.setTaskStatus(status);
        repository.save(assest);

    }

    @Override
    @Transactional
    public boolean softRealEstateAssestServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}