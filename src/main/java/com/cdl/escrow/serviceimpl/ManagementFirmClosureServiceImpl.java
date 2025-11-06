package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import com.cdl.escrow.entity.ManagementFirmClosure;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmClosureMapper;
import com.cdl.escrow.repository.ManagementFirmClosureRepository;
import com.cdl.escrow.service.ManagementFirmClosureService;
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
public class ManagementFirmClosureServiceImpl implements ManagementFirmClosureService {

   private final ManagementFirmClosureRepository repository;

    private final ManagementFirmClosureMapper mapper;



    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmClosureDTO> getAllRealEstateAssestClosure(Pageable pageable) {
        log.debug("Fetching all Real EstateAssest closure , page: {}", pageable.getPageNumber());
        Page<ManagementFirmClosure> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmClosureDTO> getRealEstateAssestClosureById(Long id) {
        log.debug("Fetching Real EstateAssest closure with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmClosureDTO saveRealEstateAssestClosure(ManagementFirmClosureDTO managementFirmClosureDTO) {
        log.info("Saving new Real EstateAssest closure");
        ManagementFirmClosure entity = mapper.toEntity(managementFirmClosureDTO);
        ManagementFirmClosure saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmClosureDTO updateRealEstateAssestClosure(Long id, ManagementFirmClosureDTO managementFirmClosureDTO) {
        log.info("Updating Real EstateAssest closure with ID: {}", id);

        ManagementFirmClosure existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Real EstateAssest closure not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmClosure toUpdate = mapper.toEntity(managementFirmClosureDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmClosure updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteRealEstateAssestClosureById(Long id) {
        log.info("Deleting Real EstateAssest closure  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest closure not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softRealEstateAssestClosureServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
