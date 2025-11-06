package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.ManagementFirmFinancialSummaryCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmFinancialSummaryCriteriaService;
import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmFinancialSummaryRepository;
import com.cdl.escrow.service.ManagementFirmFinancialSummaryService;
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

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/management-firms-financial-summary")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-financial-summary-controller", description = "APIs for management firms financial summary")
public class ManagementFirmFinancialSummaryController {

     private final ManagementFirmFinancialSummaryService managementFirmFinancialSummaryService;

     private final ManagementFirmFinancialSummaryCriteriaService managementFirmFinancialSummaryCriteriaService;

    private final ManagementFirmFinancialSummaryRepository repository;

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_FINANCIAL_SUMMARY";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmFinancialSummaryDTO>> getAllManagementFirmFinancialSummaryByCriteria(@ParameterObject ManagementFirmFinancialSummaryCriteria criteria,
                                                                                                                    @ParameterObject  Pageable pageable) {
        Page<ManagementFirmFinancialSummaryDTO> page = managementFirmFinancialSummaryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmFinancialSummaryDTO>> getAllManagementFirmFinancialSummary(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Management Firm financial summary, page: {}", pageable.getPageNumber());
        Page<ManagementFirmFinancialSummaryDTO> page = managementFirmFinancialSummaryService.getAllManagementFirmFinancialSummary(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmFinancialSummaryDTO> saveManagementFirmFinancialSummary(
            @Valid @RequestBody ManagementFirmFinancialSummaryDTO dto) {
        log.info("Creating new Management Firm financial summary");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate financial summary cannot already have an ID", ENTITY_NAME , "idexists");
        }
        ManagementFirmFinancialSummaryDTO saved = managementFirmFinancialSummaryService.saveManagementFirmFinancialSummary(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmFinancialSummaryDTO> getManagementFirmFinancialSummaryById(@PathVariable Long id) {
        log.info("Fetching Management Firm financial summary with ID: {}", id);
        return managementFirmFinancialSummaryService.getManagementFirmFinancialSummaryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest financial summary not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmFinancialSummaryDTO> updateManagementFirmFinancialSummary(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmFinancialSummaryDTO dto) {
        log.info("Updating Management Firm financial summary with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmFinancialSummaryDTO updated = managementFirmFinancialSummaryService.updateManagementFirmFinancialSummary(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManagementFirmFinancialSummaryById(@PathVariable Long id) {
        log.info("Deleting Management Firm financial summary with ID: {}", id);
        boolean deleted = managementFirmFinancialSummaryService.deleteManagementFirmFinancialSummaryById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFinancialSummary deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFinancialSummary deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteManagementFirmFinancialSummaryServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmFinancialSummary with ID: {}", id);

        boolean deleted = managementFirmFinancialSummaryService.softManagementFirmFinancialSummaryServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFinancialSummary soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFinancialSummary soft deletion failed - ID: " + id);
        }
    }
}
