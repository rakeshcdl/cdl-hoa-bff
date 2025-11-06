package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import com.cdl.escrow.entity.ManagementFirmHistory;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmFeeHistoryMapper;
import com.cdl.escrow.repository.ManagementFirmFeeHistoryRepository;
import com.cdl.escrow.service.ManagementFirmFeeHistoryService;
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
public class ManagementFirmFeeHistoryServiceImpl implements ManagementFirmFeeHistoryService {

    private final ManagementFirmFeeHistoryRepository repository;

    private final ManagementFirmFeeHistoryMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmHistoryDTO> getAllManagementFirmFeeHistory(Pageable pageable) {
        log.debug("Fetching all Management Firmfee history , page: {}", pageable.getPageNumber());
        Page<ManagementFirmHistory> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmHistoryDTO> getManagementFirmFeeHistoryById(Long id) {
        log.debug("Fetching Management Firmfee history with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmHistoryDTO saveManagementFirmFeeHistory(ManagementFirmHistoryDTO managementFirmHistoryDTO) {
        log.info("Saving new Management Firmfee history");
        ManagementFirmHistory entity = mapper.toEntity(managementFirmHistoryDTO);
        ManagementFirmHistory saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmHistoryDTO updateManagementFirmFeeHistory(Long id, ManagementFirmHistoryDTO managementFirmHistoryDTO) {
        log.info("Updating Management Firmfee history with ID: {}", id);

        ManagementFirmHistory existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Management Firmfee history not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmHistory toUpdate = mapper.toEntity(managementFirmHistoryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmHistory updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteManagementFirmFeeHistoryById(Long id) {
        log.info("Deleting Management Firmfee history  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Management Firmfee history not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softManagementFirmFeeHistoryServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}