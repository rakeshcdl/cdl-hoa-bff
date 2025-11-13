package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import com.cdl.escrow.entity.ManagementFirmPaymentPlan;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmPaymentPlanMapper;
import com.cdl.escrow.repository.ManagementFirmPaymentPlanRepository;
import com.cdl.escrow.service.ManagementFirmPaymentPlanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagementFirmPaymentPlanServiceImpl implements ManagementFirmPaymentPlanService {

    private final ManagementFirmPaymentPlanRepository repository;

    private final ManagementFirmPaymentPlanMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmPaymentPlanDTO> getAllManagementFirmPaymentPlan(Pageable pageable) {
        log.debug("Fetching all Management Firm Payment Plan, page: {}", pageable.getPageNumber());
        Page<ManagementFirmPaymentPlan> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmPaymentPlanDTO> getManagementFirmPaymentPlanById(Long id) {
        log.debug("Fetching Management Firm Payment Plan with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO saveManagementFirmPaymentPlan(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO) {
        log.info("Saving new Management Firm Payment Plan");
        ManagementFirmPaymentPlan entity = mapper.toEntity(managementFirmPaymentPlanDTO);
        ManagementFirmPaymentPlan saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO updateManagementFirmPaymentPlan(Long id, ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO) {
        log.info("Updating Real EstateAssest Payment Plan with ID: {}", id);

        ManagementFirmPaymentPlan existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Real EstateAssest Payment Plan not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmPaymentPlan toUpdate = mapper.toEntity(managementFirmPaymentPlanDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmPaymentPlan updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteManagementFirmPaymentPlanById(Long id) {
        log.info("Deleting Real EstateAssest Payment Plan with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest Payment Plan not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO saveAllManagementFirmPaymentPlan(List<ManagementFirmPaymentPlanDTO> dto) {
        ManagementFirmPaymentPlan saved = null;
        for(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO : dto) {
            ManagementFirmPaymentPlan entity = mapper.toEntity(managementFirmPaymentPlanDTO);
             saved = repository.save(entity);
        }
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public boolean softManagementFirmPaymentPlanServiceById(Long id) {
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
