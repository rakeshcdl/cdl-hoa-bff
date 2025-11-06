package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import com.cdl.escrow.entity.ManagementFirmFee;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmFeeMapper;
import com.cdl.escrow.repository.ManagementFirmFeeRepository;
import com.cdl.escrow.service.ManagementFirmFeeService;
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
public class ManagementFirmFeeServiceImpl implements ManagementFirmFeeService {

   private final ManagementFirmFeeRepository repository;

   private final ManagementFirmFeeMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmFeeDTO> getAllManagementFirmFee(Pageable pageable) {
        log.debug("Fetching all Management Firm fee , page: {}", pageable.getPageNumber());
        Page<ManagementFirmFee> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmFeeDTO> getManagementFirmFeeById(Long id) {
        log.debug("Fetching Management Firm fee with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmFeeDTO saveManagementFirmFee(ManagementFirmFeeDTO managementFirmFeeDTO) {
        log.info("Saving new Management Firm fee");
        ManagementFirmFee entity = mapper.toEntity(managementFirmFeeDTO);
        ManagementFirmFee saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmFeeDTO updateManagementFirmFee(Long id, ManagementFirmFeeDTO managementFirmFeeDTO) {
        log.info("Updating Management Firm fee with ID: {}", id);

        ManagementFirmFee existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Management Firm fee not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmFee toUpdate = mapper.toEntity(managementFirmFeeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmFee updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteManagementFirmFeeById(Long id) {
        log.info("Deleting Management Firm Beneficiary  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Management Firm Beneficiary not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softManagementFirmFeeServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
