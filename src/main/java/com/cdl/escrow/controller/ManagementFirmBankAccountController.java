package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.ManagementFirmBankAccountCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmBankAccountCriteriaService;
import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmBankAccountRepository;
import com.cdl.escrow.service.ManagementFirmBankAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@RequestMapping("/api/v1/management-firms-bank-account")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-bank-account-controller", description = "APIs for management firms bank account")
public class ManagementFirmBankAccountController {

    private final ManagementFirmBankAccountService managementFirmBankAccountService;

    private final ManagementFirmBankAccountRepository repository;

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_BANK_ACCOUNT";

    private final ManagementFirmBankAccountCriteriaService managementFirmBankAccountCriteriaService;

    @GetMapping
    public ResponseEntity<Page<ManagementFirmBankAccountDTO>> getAllRealEstateBankAccountByCriteria(@ParameterObject ManagementFirmBankAccountCriteria criteria,
                                                                                                    @ParameterObject  Pageable pageable) {
        Page<ManagementFirmBankAccountDTO> page = managementFirmBankAccountCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmBankAccountDTO>> getAllRealEstateBankAccount(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all real estate bank account, page: {}", pageable.getPageNumber());
        Page<ManagementFirmBankAccountDTO> page = managementFirmBankAccountService.getAllRealEstateBankAccount(pageable);
        return ResponseEntity.ok(page);
    }
    @PostMapping
    public ResponseEntity<ManagementFirmBankAccountDTO> saveRealEstateBankAccount(
            @Valid @RequestBody ManagementFirmBankAccountDTO dto) {
        log.info("Creating new real estate bank account");

        ManagementFirmBankAccountDTO saved = managementFirmBankAccountService.saveRealEstateBankAccount(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmBankAccountDTO> getRealEstateBankAccountById(@PathVariable Long id) {
        log.info("Fetching real estate bank account with ID: {}", id);
        return managementFirmBankAccountService.getRealEstateBankAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate bank account not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmBankAccountDTO> updateRealEstateBankAccount(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmBankAccountDTO dto) {
        log.info("Updating real estate bank account with ID: {}", id);

        ManagementFirmBankAccountDTO updated = managementFirmBankAccountService.updateRealEstateBankAccount(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRealEstateBankAccountById(@PathVariable Long id) {
        log.info("Deleting real estate bank account with ID: {}", id);
        boolean deleted = managementFirmBankAccountService.deleteRealEstateBankAccountById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmBankAccount deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmBankAccount deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteRealEstateBankAccountServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmBankAccount with ID: {}", id);

        boolean deleted = managementFirmBankAccountService.softRealEstateBankAccountServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmBankAccount soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmBankAccount soft deletion failed - ID: " + id);
        }
    }

}
