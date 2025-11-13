package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryContactDetailDTO;
import com.cdl.escrow.entity.OwnerRegistryContactDetail;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryContactDetailMapper;
import com.cdl.escrow.repository.OwnerRegistryContactDetailRepository;
import com.cdl.escrow.repository.TaskStatusRepository;
import com.cdl.escrow.service.OwnerRegistryContactDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OwnerRegistryContactDetailServiceImpl implements OwnerRegistryContactDetailService {
    private final OwnerRegistryContactDetailRepository repository;

    private final OwnerRegistryContactDetailMapper mapper;

    private final TaskStatusRepository taskStatusRepository;


    @Override
    public Page<OwnerRegistryContactDetailDTO> getAllOwnerRegistryContactDetail(Pageable pageable) {
        log.debug("Fetching all Owner Registry, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryContactDetail> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    public Optional<OwnerRegistryContactDetailDTO> getOwnerRegistryContactDetailById(Long id) {
        log.debug("Fetching Owner Registry Contact Detailwith ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    public OwnerRegistryContactDetailDTO saveOwnerRegistryContactDetail(OwnerRegistryContactDetailDTO ownerRegistryContactDetailDTO) {
        log.info("Saving new Owner Registry");
        OwnerRegistryContactDetail entity = mapper.toEntity(ownerRegistryContactDetailDTO);
        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("IN_PROGRESS")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "IN_PROGRESS"));
        entity.setTaskStatus(ts);

        // set any default flags if needed
        entity.setDeleted(false);
        // entity.setEnabled(true/false) as per business rule
        entity.setEnabled(true);
        OwnerRegistryContactDetail saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public OwnerRegistryContactDetailDTO updateOwnerRegistryContactDetail(Long id, OwnerRegistryContactDetailDTO ownerRegistryContactDetailDTO) {
        log.info("Updating Owner Registry Contact Detailwith ID: {}", id);

        OwnerRegistryContactDetail existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry Contact Detail not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistryContactDetail toUpdate = mapper.toEntity(ownerRegistryContactDetailDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved
        // set default task status to IN-PROGRESS on create
        TaskStatus ts = taskStatusRepository.findByName("DRAFT")
                .orElseThrow(() -> new EntityNotFoundException(
                        "TaskStatus not found: " + "DRAFT"));
        toUpdate.setTaskStatus(ts);
        OwnerRegistryContactDetail updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    public Boolean deleteOwnerRegistryContactDetailById(Long id) {
        log.info("Deleting Owner Registry Contact Detail with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry Contact Detail not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean softOwnerRegistryContactDetailServiceById(Long id) {
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
    public void finalizeOwnerRegistryContactDetail(Long moduleId, TaskStatus status) {

    }
}
