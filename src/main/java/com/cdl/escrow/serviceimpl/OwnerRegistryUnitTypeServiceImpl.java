package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryUnitTypeDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitType;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryUnitTypeMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitTypeRepository;
import com.cdl.escrow.service.OwnerRegistryUnitTypeService;
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
public class OwnerRegistryUnitTypeServiceImpl implements OwnerRegistryUnitTypeService {

   private final OwnerRegistryUnitTypeRepository repository;

    private final OwnerRegistryUnitTypeMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryUnitTypeDTO> getAllOwnerRegistryUnitType(Pageable pageable) {
        log.debug("Fetching all Owner Registry unit type , page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitType> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryUnitTypeDTO> getOwnerRegistryUnitTypeById(Long id) {
        log.debug("Fetching Owner Registry unit type with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitTypeDTO saveOwnerRegistryUnitType(OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO) {
        log.info("Saving new Owner Registry unit type");
        OwnerRegistryUnitType entity = mapper.toEntity(ownerRegistryUnitTypeDTO);
        OwnerRegistryUnitType saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitTypeDTO updateOwnerRegistryUnitType(Long id, OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO) {
        log.info("Updating Owner Registry unit type with ID: {}", id);

        OwnerRegistryUnitType existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry unit type not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistryUnitType toUpdate = mapper.toEntity(ownerRegistryUnitTypeDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        OwnerRegistryUnitType updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteOwnerRegistryUnitTypeById(Long id) {
        log.info("Deleting Owner Registry unit type with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry unit type not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryUnitTypeServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
