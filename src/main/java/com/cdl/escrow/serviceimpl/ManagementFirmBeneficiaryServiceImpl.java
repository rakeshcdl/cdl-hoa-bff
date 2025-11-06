package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.entity.ManagementFirmBeneficiary;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmBeneficiaryMapper;
import com.cdl.escrow.repository.ManagementFirmBeneficiaryRepository;
import com.cdl.escrow.repository.ManagementFirmRepository;
import com.cdl.escrow.service.ManagementFirmBeneficiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManagementFirmBeneficiaryServiceImpl implements ManagementFirmBeneficiaryService {

    private final ManagementFirmBeneficiaryRepository repository;

    private final ManagementFirmBeneficiaryMapper mapper;

    private final ManagementFirmRepository managementFirmRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmBeneficiaryDTO> getAllRealEstateAssestBeneficiary(Pageable pageable) {
        log.debug("Fetching all Real EstateAssest Beneficiary , page: {}", pageable.getPageNumber());
        Page<ManagementFirmBeneficiary> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmBeneficiaryDTO> getRealEstateAssestBeneficiaryById(Long id) {
        log.debug("Fetching Real EstateAssest Beneficiary with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


//    @Override
//    @Transactional
//    public ManagementFirmBeneficiaryDTO saveRealEstateAssestBeneficiary(ManagementFirmBeneficiaryDTO realEstateAssestBeneficiaryDTO) {
//        log.info("Saving new Real EstateAssest Beneficiary");
//        ManagementFirmBeneficiary entity = mapper.toEntity(realEstateAssestBeneficiaryDTO);
//        ManagementFirmBeneficiary saved = repository.save(entity);
//        return mapper.toDto(saved);
//    }


    @Override
    @Transactional
    public ManagementFirmBeneficiaryDTO saveRealEstateAssestBeneficiary(ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO) {
        log.info("Saving new real estate beneficiary");

        // Step 1: Save the main entity without associations
        ManagementFirmBeneficiary entity = mapper.toEntity(managementFirmBeneficiaryDTO);
        entity.setManagementFirms(new HashSet<>()); // Clear associations temporarily
        ManagementFirmBeneficiary saved = repository.save(entity);

        // Step 2: Add associations and save again
        if (managementFirmBeneficiaryDTO.getManagementFirmDTO() != null &&
                !managementFirmBeneficiaryDTO.getManagementFirmDTO().isEmpty()) {

            Set<ManagementFirm> managementFirms = new HashSet<>();

            for (ManagementFirmDTO bpDto : managementFirmBeneficiaryDTO.getManagementFirmDTO()) {
                ManagementFirm bp = managementFirmRepository.findById(bpDto.getId())
                        .orElseThrow(() -> new RuntimeException("Real Estate Assest not found"));
                managementFirms.add(bp);

                // Set bidirectional relationship
                bp.getRealEstateAssestBeneficiaries().add(saved);
            }

            saved.setManagementFirms(managementFirms);
            saved = repository.save(saved);

            // Save the other side as well
            managementFirmRepository.saveAll(managementFirms);
        }

        return mapper.toDto(saved);
    }


    /*@Override
    @Transactional
    public ManagementFirmBeneficiaryDTO updateRealEstateAssestBeneficiary(Long id, ManagementFirmBeneficiaryDTO realEstateAssestBeneficiaryDTO) {
        log.info("Updating Real EstateAssest Beneficiary with ID: {}", id);

        ManagementFirmBeneficiary existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Real EstateAssest Beneficiary not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmBeneficiary toUpdate = mapper.toEntity(realEstateAssestBeneficiaryDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmBeneficiary updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }*/

    @Override
    @Transactional
    public ManagementFirmBeneficiaryDTO updateRealEstateAssestBeneficiary(Long id, ManagementFirmBeneficiaryDTO managementFirmBeneficiaryDTO) {
        log.info("Updating real estate beneficiary with id: {}", id);

        // Step 1: Get existing entity
        ManagementFirmBeneficiary existingEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ManagementFirmBeneficiary not found with id: " + id));

        // Step 2: Clear existing associations from both sides
        if (existingEntity.getManagementFirms() != null) {
            for (ManagementFirm existingAssest : existingEntity.getManagementFirms()) {
                existingAssest.getRealEstateAssestBeneficiaries().remove(existingEntity);
            }
            managementFirmRepository.saveAll(existingEntity.getManagementFirms());
            existingEntity.getManagementFirms().clear();
        }

        // Step 3: Update entity properties (excluding associations)
        ManagementFirmBeneficiary updatedEntity = mapper.toEntity(managementFirmBeneficiaryDTO);
        updatedEntity.setId(existingEntity.getId()); // Preserve the ID
        updatedEntity.setManagementFirms(new HashSet<>()); // Clear associations temporarily

        ManagementFirmBeneficiary saved = repository.save(updatedEntity);

        // Step 4: Add new associations
        if (managementFirmBeneficiaryDTO.getManagementFirmDTO() != null &&
                !managementFirmBeneficiaryDTO.getManagementFirmDTO().isEmpty()) {

            Set<ManagementFirm> managementFirms = new HashSet<>();

            for (ManagementFirmDTO assestDto : managementFirmBeneficiaryDTO.getManagementFirmDTO()) {
                ManagementFirm assest = managementFirmRepository.findById(assestDto.getId())
                        .orElseThrow(() -> new RuntimeException("Real Estate Assest not found with id: " + assestDto.getId()));
                managementFirms.add(assest);

                // Set bidirectional relationship
                assest.getRealEstateAssestBeneficiaries().add(saved);
            }

            saved.setManagementFirms(managementFirms);
            saved = repository.save(saved);

            // Save the other side as well
            managementFirmRepository.saveAll(managementFirms);
        }

        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public Boolean deleteRealEstateAssestBeneficiaryById(Long id) {
        log.info("Deleting Real EstateAssest Beneficiary  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Real EstateAssest Beneficiary not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softRealEstateAssestBeneficiaryServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}