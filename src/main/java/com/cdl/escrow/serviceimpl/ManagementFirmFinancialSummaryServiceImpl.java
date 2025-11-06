package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import com.cdl.escrow.entity.ManagementFirmFinancialSummary;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmFinancialSummaryMapper;
import com.cdl.escrow.repository.ManagementFirmFinancialSummaryRepository;
import com.cdl.escrow.service.ManagementFirmFinancialSummaryService;
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
public class ManagementFirmFinancialSummaryServiceImpl implements ManagementFirmFinancialSummaryService {

    private final ManagementFirmFinancialSummaryRepository repository;

    private final ManagementFirmFinancialSummaryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmFinancialSummaryDTO> getAllRealEstateAssestFinancialSummary(Pageable pageable) {
        log.debug("Fetching all Real EstateAssest financial summary , page: {}", pageable.getPageNumber());
        Page<ManagementFirmFinancialSummary> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmFinancialSummaryDTO> getRealEstateAssestFinancialSummaryById(Long id) {
        log.debug("Fetching Real EstateAssest financial summary with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmFinancialSummaryDTO saveRealEstateAssestFinancialSummary(ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO) {
        log.info("Saving new Real EstateAssest financial summary");
        ManagementFirmFinancialSummary entity = mapper.toEntity(managementFirmFinancialSummaryDTO);
        ManagementFirmFinancialSummary saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmFinancialSummaryDTO updateRealEstateAssestFinancialSummary(Long id, ManagementFirmFinancialSummaryDTO managementFirmFinancialSummaryDTO) {
        log.info("Updating Real EstateAssest financial summary with ID: {}", id);

        ManagementFirmFinancialSummary existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Real EstateAssest financial summary not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmFinancialSummary toUpdate = mapper.toEntity(managementFirmFinancialSummaryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmFinancialSummary updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteRealEstateAssestFinancialSummaryById(Long id) {
        log.info("Deleting Real EstateAssest financial summary  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest financial summary not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softRealEstateAssestFinancialSummaryServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
