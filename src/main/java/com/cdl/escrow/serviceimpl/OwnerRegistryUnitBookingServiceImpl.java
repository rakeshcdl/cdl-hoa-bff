package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryUnitBookingDTO;
import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import com.cdl.escrow.entity.OwnerRegistryUnit;
import com.cdl.escrow.entity.OwnerRegistryUnitBooking;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryUnitBookingMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitBookingRepository;
import com.cdl.escrow.repository.OwnerRegistryUnitRepository;
import com.cdl.escrow.service.OwnerRegistryUnitBookingService;
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
public class OwnerRegistryUnitBookingServiceImpl implements OwnerRegistryUnitBookingService {

    private final OwnerRegistryUnitBookingRepository repository;

   private final OwnerRegistryUnitBookingMapper mapper;

   private final OwnerRegistryUnitRepository ownerRegistryUnitRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryUnitBookingDTO> getAllOwnerRegistryUnitBooking(Pageable pageable) {
        log.debug("Fetching all Owner Registry unit booking, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitBooking> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryUnitBookingDTO> getOwnerRegistryUnitBookingById(Long id) {
        log.debug("Fetching Owner Registry unit booking with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitBookingDTO saveOwnerRegistryUnitBooking(OwnerRegistryUnitBookingDTO dto) {

        log.info("Saving new Owner Registry unit booking");
      //  OwnerRegistryUnitBooking entity = mapper.toEntity(capitalPartnerUnitBookingDTO);
      //  OwnerRegistryUnitBooking saved = repository.save(entity);


        OwnerRegistryUnitBooking unit = mapper.toEntity(dto);
        OwnerRegistryUnitBooking savedUnit = repository.save(unit);

        // 3. If DTO has capitalPartnerDTOS, update owning side
        if (dto.getOwnerRegistryUnitDTOS() != null && !dto.getOwnerRegistryUnitDTOS().isEmpty()) {

            for (OwnerRegistryUnitDTO cpDto : dto.getOwnerRegistryUnitDTOS()) {
                if (cpDto.getId() == null) continue;

                // Load partner as a managed reference (no SELECT)
                Optional<OwnerRegistryUnit> partners = ownerRegistryUnitRepository.findById(cpDto.getId());

                // Set owning side
                if(partners.isPresent())
                {
                    log.info(String.valueOf(partners.get().getId()));
                    partners.get().setOwnerRegistryUnitBooking(savedUnit);
                    ownerRegistryUnitRepository.save(partners.get());
                }
            }

        }
        return mapper.toDto(savedUnit);
    }


    @Override
    @Transactional
    public OwnerRegistryUnitBookingDTO updateOwnerRegistryUnitBooking(Long id, OwnerRegistryUnitBookingDTO dto) {
        log.info("Updating Owner Registry unit booking with ID: {}", id);

        OwnerRegistryUnitBooking existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Owner Registry unit booking not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
       /* OwnerRegistryUnitBooking toUpdate = mapper.toEntity(dto);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        OwnerRegistryUnitBooking updated = repository.save(toUpdate);*/


        OwnerRegistryUnitBooking unit = mapper.toEntity(dto);
        unit.setId(existing.getId()); // Ensure the correct ID is preserved
        OwnerRegistryUnitBooking savedUnit = repository.save(unit);

        // 3. If DTO has capitalPartnerDTOS, update owning side
        if (dto.getOwnerRegistryUnitDTOS() != null && !dto.getOwnerRegistryUnitDTOS().isEmpty()) {

            for (OwnerRegistryUnitDTO cpDto : dto.getOwnerRegistryUnitDTOS()) {
                if (cpDto.getId() == null) continue;

                // Load partner as a managed reference (no SELECT)
                Optional<OwnerRegistryUnit> partners = ownerRegistryUnitRepository.findById(cpDto.getId());

                // Set owning side
                if(partners.isPresent())
                {
                    log.info(String.valueOf(partners.get().getId()));
                    partners.get().setOwnerRegistryUnitBooking(savedUnit);
                    ownerRegistryUnitRepository.save(partners.get());
                }
            }

        }
        return mapper.toDto(savedUnit);



       // return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteOwnerRegistryUnitBookingById(Long id) {
        log.info("Deleting Owner Registry unit booking  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Owner Registry unit booking not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryUnitBookingServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
