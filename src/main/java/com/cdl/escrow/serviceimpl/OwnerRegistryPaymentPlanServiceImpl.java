package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryPaymentPlanDTO;
import com.cdl.escrow.entity.OwnerRegistryPaymentPlan;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryPaymentPlanMapper;
import com.cdl.escrow.repository.OwnerRegistryPaymentPlanRepository;
import com.cdl.escrow.service.OwnerRegistryPaymentPlanService;
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
public class OwnerRegistryPaymentPlanServiceImpl implements OwnerRegistryPaymentPlanService {

    private final OwnerRegistryPaymentPlanRepository repository;

    private final OwnerRegistryPaymentPlanMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryPaymentPlanDTO> getAllOwnerRegistryPaymentPlan(Pageable pageable) {
        log.debug("Fetching all Owner Registry Payment Plan, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryPaymentPlan> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryPaymentPlanDTO> getROwnerRegistryPaymentPlanById(Long id) {
        log.debug("Fetching Owner Registry Payment Plan with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public OwnerRegistryPaymentPlanDTO saveOwnerRegistryPaymentPlan(OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO) {
        log.info("Saving new Owner Registry Payment Plan");
        OwnerRegistryPaymentPlan entity = mapper.toEntity(ownerRegistryPaymentPlanDTO);
        OwnerRegistryPaymentPlan saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public OwnerRegistryPaymentPlanDTO updateOwnerRegistryPaymentPlan(Long id, OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO) {
        log.info("Updating Owner Registry Payment Plan with ID: {}", id);

        OwnerRegistryPaymentPlan existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry Payment Plan not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistryPaymentPlan toUpdate = mapper.toEntity(ownerRegistryPaymentPlanDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        OwnerRegistryPaymentPlan updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public Boolean deleteOwnerRegistryPaymentPlanById(Long id) {
        log.info("Deleting Owner Registry Payment Plan with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry Payment Plan not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public OwnerRegistryPaymentPlanDTO saveAllOwnerRegistryPaymentPlan(List<OwnerRegistryPaymentPlanDTO> ownerRegistryPaymentPlanDTOS) {
        OwnerRegistryPaymentPlan saved = null;
        for(OwnerRegistryPaymentPlanDTO ownerRegistryPaymentPlanDTO : ownerRegistryPaymentPlanDTOS) {
            OwnerRegistryPaymentPlan entity = mapper.toEntity(ownerRegistryPaymentPlanDTO);
            saved = repository.save(entity);
        }
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryPaymentPlanServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
