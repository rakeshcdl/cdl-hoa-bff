package com.cdl.escrow.serviceimpl;

import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import com.cdl.escrow.entity.ManagementFirmBankAccount;
import com.cdl.escrow.exception.ApplicationConfigurationNotFoundException;
import com.cdl.escrow.mapper.ManagementFirmBankAccountMapper;
import com.cdl.escrow.repository.ManagementFirmBankAccountRepository;
import com.cdl.escrow.service.ManagementFirmBankAccountService;
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
public class ManagementFirmBankAccountServiceImpl implements ManagementFirmBankAccountService {

   private final ManagementFirmBankAccountRepository repository;

   private final ManagementFirmBankAccountMapper mapper;


    @Override
    @Transactional(readOnly = true)
    public Page<ManagementFirmBankAccountDTO> getAllManagementFirmBankAccount(Pageable pageable) {
        log.debug("Fetching all Management Firm Assest bank account , page: {}", pageable.getPageNumber());
        Page<ManagementFirmBankAccount> page = repository.findAll(pageable);
        return new PageImpl<>(
                page.map(mapper::toDto).getContent(),
                pageable,
                page.getTotalElements()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ManagementFirmBankAccountDTO> getManagementFirmBankAccountById(Long id) {
        log.debug("Fetching Management FirmAssest bank account with ID: {}", id);
        return repository.findById(id)
                .map(mapper::toDto);
    }


    @Override
    @Transactional
    public ManagementFirmBankAccountDTO saveManagementFirmBankAccount(ManagementFirmBankAccountDTO managementFirmBankAccountDTO) {
        log.info("Saving new Management FirmAssest bank account");
        ManagementFirmBankAccount entity = mapper.toEntity(managementFirmBankAccountDTO);
        ManagementFirmBankAccount saved = repository.save(entity);
        return mapper.toDto(saved);
    }


    @Override
    @Transactional
    public ManagementFirmBankAccountDTO updateManagementFirmBankAccount(Long id, ManagementFirmBankAccountDTO managementFirmBankAccountDTO) {
        log.info("Updating Management FirmAssest bank account with ID: {}", id);

        ManagementFirmBankAccount existing = repository.findById(id)
                .orElseThrow(() -> new ApplicationConfigurationNotFoundException("Management FirmAssest bank account not found with ID: " + id));

        // Optionally, update only mutable fields instead of full replacement
        ManagementFirmBankAccount toUpdate = mapper.toEntity(managementFirmBankAccountDTO);
        toUpdate.setId(existing.getId()); // Ensure the correct ID is preserved

        ManagementFirmBankAccount updated = repository.save(toUpdate);
        return mapper.toDto(updated);
    }


    @Override
    @Transactional
    public Boolean deleteManagementFirmBankAccountById(Long id) {
        log.info("Deleting Management FirmAssest bank account  with ID: {}", id);

        if (!repository.existsById(id)) {
            throw new ApplicationConfigurationNotFoundException("Management FirmAssest bank account not found with ID: " + id);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public boolean softManagementFirmBankAccountServiceById(Long id) {
        return repository.findByIdAndDeletedFalse(id).map(entity -> {
            entity.setDeleted(true);
            repository.save(entity);
            return true;
        }).orElse(false);
    }
}
