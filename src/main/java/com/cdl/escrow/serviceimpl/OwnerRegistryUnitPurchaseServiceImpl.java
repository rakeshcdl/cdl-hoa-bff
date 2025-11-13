package com.cdl.escrow.serviceimpl;


import com.cdl.escrow.dto.OwnerRegistryUnitPurchaseDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitPurchase;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryUnitPurchaseMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitPurchaseRepository;
import com.cdl.escrow.service.OwnerRegistryUnitPurchaseService;
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
public class OwnerRegistryUnitPurchaseServiceImpl implements OwnerRegistryUnitPurchaseService {
    private final OwnerRegistryUnitPurchaseRepository repository;

    private final OwnerRegistryUnitPurchaseMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryUnitPurchaseDTO> getAllOwnerRegistryUnitPurchase(Pageable pageable) {
        log.debug("Fetching all Owner Registry unit purchase, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitPurchase> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryUnitPurchaseDTO> getOwnerRegistryUnitPurchaseById(Long id) {
        log.debug("Fetching Owner Registry unit purchase with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitPurchaseDTO saveOwnerRegistryUnitPurchase(OwnerRegistryUnitPurchaseDTO ownerRegistryUnitPurchaseDTO) {
        log.info("Saving new Owner Registry unit purchase");
        OwnerRegistryUnitPurchase entity = mapper.toEntity(ownerRegistryUnitPurchaseDTO);
        OwnerRegistryUnitPurchase saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitPurchaseDTO updateOwnerRegistryUnitPurchase(Long id, OwnerRegistryUnitPurchaseDTO ownerRegistryUnitPurchaseDTO) {
        log.info("Updating Owner Registry unit purchase with ID: {}", id);

        OwnerRegistryUnitPurchase existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry unit booking not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistryUnitPurchase toUpdate = mapper.toEntity(ownerRegistryUnitPurchaseDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        OwnerRegistryUnitPurchase updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteOwnerRegistryUnitPurchaseById(Long id) {
        log.info("Deleting Owner Registry unit purchase  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry unit purchase not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryUnitPurchaseServiceById(Long id) {
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
