package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.ManagementFirmPaymentPlanCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmPaymentPlanCriteriaService;
import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmPaymentPlanRepository;
import com.cdl.escrow.service.ManagementFirmPaymentPlanService;
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

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/management-firms-payment-plan")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-payment-plan-controller", description = "APIs for management firms payment plan")
public class ManagementFirmPaymentPlanController {

    private final ManagementFirmPaymentPlanService managementFirmPaymentPlanService;

    private final ManagementFirmPaymentPlanRepository repository;

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_PAYMENT_PLAN";

    private final ManagementFirmPaymentPlanCriteriaService managementFirmPaymentPlanCriteriaService;


    @GetMapping
    public ResponseEntity<Page<ManagementFirmPaymentPlanDTO>> getAllManagementFirmPaymentPlanByCriteria(@ParameterObject ManagementFirmPaymentPlanCriteria criteria,
                                                                                                          @ParameterObject  Pageable pageable) {
        Page<ManagementFirmPaymentPlanDTO> page = managementFirmPaymentPlanCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmPaymentPlanDTO>> getAllManagementFirmPaymentPlan(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Management Firm Payment Plan, page: {}", pageable.getPageNumber());
        Page<ManagementFirmPaymentPlanDTO> page = managementFirmPaymentPlanService.getAllManagementFirmPaymentPlan(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmPaymentPlanDTO> saveManagementFirmPaymentPlan(
            @Valid @RequestBody ManagementFirmPaymentPlanDTO dto) {
        log.info("Creating new Management Firm Payment Plan");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Management Firms Payment Plan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmPaymentPlanDTO saved = managementFirmPaymentPlanService.saveManagementFirmPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> getManagementFirmPaymentPlanById(@PathVariable Long id) {
        log.info("Fetching Management Firm Payment Plan with ID: {}", id);
        return managementFirmPaymentPlanService.getManagementFirmPaymentPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> updateManagementFirmPaymentPlan(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmPaymentPlanDTO dto) {
        log.info("Updating Management Firm Payment Plan with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmPaymentPlanDTO updated = managementFirmPaymentPlanService.updateManagementFirmPaymentPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManagementFirmPaymentPlanById(@PathVariable Long id) {
        log.info("Deleting Management Firm Payment Plan with ID: {}", id);
        boolean deleted = managementFirmPaymentPlanService.deleteManagementFirmPaymentPlanById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirm Payment Plan deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirm Payment Plan  deletion failed - ID: " + id);
        }
    }

    @PostMapping("/save-all")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> saveAllManagementFirmPaymentPlan(
            @Valid @RequestBody List<ManagementFirmPaymentPlanDTO> dto) {
        log.info("Creating all new Management Firm Payment Plan");

        ManagementFirmPaymentPlanDTO saved = managementFirmPaymentPlanService.saveAllManagementFirmPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteManagementFirmPaymentPlanServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmPaymentPlan with ID: {}", id);

        boolean deleted = managementFirmPaymentPlanService.softManagementFirmPaymentPlanServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmPaymentPlan soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmPaymentPlan soft deletion failed - ID: " + id);
        }
    }
}
