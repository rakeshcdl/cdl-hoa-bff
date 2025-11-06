package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import com.cdl.escrow.entity.OwnerRegistry;
import com.cdl.escrow.entity.OwnerRegistryUnit;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryMapper;
import com.cdl.escrow.mapper.OwnerRegistryUnitMapper;
import com.cdl.escrow.repository.OwnerRegistryRepository;
import com.cdl.escrow.repository.OwnerRegistryUnitRepository;
import com.cdl.escrow.service.OwnerRegistryUnitService;
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
public class OwnerRegistryUnitServiceImpl implements OwnerRegistryUnitService {

   private final OwnerRegistryUnitRepository repository;

    private final OwnerRegistryUnitMapper mapper;

    private final OwnerRegistryRepository ownerRegistryRepository;

    private final OwnerRegistryMapper ownerRegistryMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryUnitDTO> getAllOwnerRegistryUnit(Pageable pageable) {
        log.debug("Fetching all Owner Registry unit , page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnit> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryUnitDTO> getOwnerRegistryUnitById(Long id) {
        log.debug("Fetching Owner Registry unit with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitDTO saveOwnerRegistryUnit(OwnerRegistryUnitDTO dto) {
        log.info("Saving new Owner Registry unit");
        OwnerRegistryUnit unit = mapper.toEntity(dto);
        OwnerRegistryUnit savedUnit = repository.save(unit);

        // 3. If DTO has capitalPartnerDTOS, update owning side
        if (dto.getOwnerRegistryDTOS() != null && !dto.getOwnerRegistryDTOS().isEmpty()) {

            for (OwnerRegistryDTO cpDto : dto.getOwnerRegistryDTOS()) {
                if (cpDto.getId() == null) continue;

                // Load partner as a managed reference (no SELECT)
                Optional<OwnerRegistry> partners = ownerRegistryRepository.findById(cpDto.getId());

                // Set owning side
                if(partners.isPresent())
                {
                    log.info(String.valueOf(partners.get().getId()));
                    partners.get().setOwnerRegistryUnit(savedUnit);
                    ownerRegistryRepository.save(partners.get());
                }
            }

        }

        return mapper.toDto(savedUnit);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitDTO updateOwnerRegistryUnit(Long id, OwnerRegistryUnitDTO ownerRegistryUnitDTO) {
        log.info("Updating Owner Registry unit with ID: {}", id);

        OwnerRegistryUnit existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry unit not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
      //  OwnerRegistryUnit toUpdate = mapper.toEntity(ownerRegistryUnitDTO);


      //  OwnerRegistryUnit updated = repository.save(toUpdate);

        OwnerRegistryUnit unit = mapper.toEntity(ownerRegistryUnitDTO);
        unit.setId(existing.getId()); // Ensure the correct ID is preserved
        OwnerRegistryUnit savedUnit = repository.save(unit);

        // 3. If DTO has capitalPartnerDTOS, update owning side
        if (ownerRegistryUnitDTO.getOwnerRegistryDTOS() != null && !ownerRegistryUnitDTO.getOwnerRegistryDTOS().isEmpty()) {

            for (OwnerRegistryDTO cpDto : ownerRegistryUnitDTO.getOwnerRegistryDTOS()) {
                if (cpDto.getId() == null) continue;

                // Load partner as a managed reference (no SELECT)
                Optional<OwnerRegistry> partners = ownerRegistryRepository.findById(cpDto.getId());

                // Set owning side
                if(partners.isPresent())
                {
                    log.info(String.valueOf(partners.get().getId()));
                    partners.get().setOwnerRegistryUnit(savedUnit);
                    ownerRegistryRepository.save(partners.get());
                }
            }

        }

        return mapper.toDto(savedUnit);
    }


    @Override
    @Transactional
    public Boolean deleteOwnerRegistryUnitById(Long id) {
        log.info("Deleting Owner Registry unit  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry unit not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryUnitServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}