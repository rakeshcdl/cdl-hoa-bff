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
    public Page<ManagementFirmPaymentPlanDTO> getAllRealEstateAssestPaymentPlan(Pageable pageable) {
        log.debug("Fetching all Real Estate Assest Payment Plan, page: {}", pageable.getPageNumber());
        Page<ManagementFirmPaymentPlan> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmPaymentPlanDTO> getRealEstateAssestPaymentPlanById(Long id) {
        log.debug("Fetching Real Estate Assest Payment Plan with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO saveRealEstateAssestPaymentPlan(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO) {
        log.info("Saving new Real Estate Assest Payment Plan");
        ManagementFirmPaymentPlan entity = mapper.toEntity(managementFirmPaymentPlanDTO);
        ManagementFirmPaymentPlan saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO updateRealEstateAssestPaymentPlan(Long id, ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO) {
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
    public Boolean deleteRealEstateAssestPaymentPlanById(Long id) {
        log.info("Deleting Real EstateAssest Payment Plan with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest Payment Plan not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ManagementFirmPaymentPlanDTO saveAllRealEstateAssestPaymentPlan(List<ManagementFirmPaymentPlanDTO> dto) {
        ManagementFirmPaymentPlan saved = null;
        for(ManagementFirmPaymentPlanDTO managementFirmPaymentPlanDTO : dto) {
            ManagementFirmPaymentPlan entity = mapper.toEntity(managementFirmPaymentPlanDTO);
             saved = repository.save(entity);
        }
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public boolean softRealEstateAssestPaymentPlanServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
