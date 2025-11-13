package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.OwnerRegistryBankInfoDTO;
import com.cdl.escrow.entity.OwnerRegistryBankInfo;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.OwnerRegistryBankInfoMapper;
import com.cdl.escrow.repository.OwnerRegistryBankInfoRepository;
import com.cdl.escrow.service.OwnerRegistryBankInfoService;
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
public class OwnerRegistryBankInfoServiceImpl implements OwnerRegistryBankInfoService {

    private final OwnerRegistryBankInfoRepository repository;

    private final OwnerRegistryBankInfoMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<OwnerRegistryBankInfoDTO> getAllOwnerRegistryBankInfo(Pageable pageable) {
        log.debug("Fetching all capital partner bank info, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryBankInfo> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<OwnerRegistryBankInfoDTO> getOwnerRegistryBankInfoById(Long id) {
        log.debug("Fetching capital partner bank info with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public OwnerRegistryBankInfoDTO saveOwnerRegistryBankInfo(OwnerRegistryBankInfoDTO ownerRegistryBankInfoDTO) {
        log.info("Saving new capital partner bank info");
        OwnerRegistryBankInfo entity = mapper.toEntity(ownerRegistryBankInfoDTO);
        OwnerRegistryBankInfo saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public OwnerRegistryBankInfoDTO updateOwnerRegistryBankInfo(Long id, OwnerRegistryBankInfoDTO ownerRegistryBankInfoDTO) {
        log.info("Updating capital partner bank info with ID: {}", id);

        OwnerRegistryBankInfo existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Build capital partner bank info not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        OwnerRegistryBankInfo toUpdate = mapper.toEntity(ownerRegistryBankInfoDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        OwnerRegistryBankInfo updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteOwnerRegistryBankInfoById(Long id) {
        log.info("Deleting capital partner bank info  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Capital partner bank info not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softOwnerRegistryBankInfoServiceById(Long id) {
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
