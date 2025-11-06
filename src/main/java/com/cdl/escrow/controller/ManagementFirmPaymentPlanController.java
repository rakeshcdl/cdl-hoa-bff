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

    private static final String ENTITY_NAME = "REAL_ESTATE_ASSEST_PAYMENT_PLAN";

    private final ManagementFirmPaymentPlanCriteriaService managementFirmPaymentPlanCriteriaService;


    @GetMapping
    public ResponseEntity<Page<ManagementFirmPaymentPlanDTO>> getAllRealEstateAssestPaymentPlanByCriteria(@ParameterObject ManagementFirmPaymentPlanCriteria criteria,
                                                                                                          @ParameterObject  Pageable pageable) {
        Page<ManagementFirmPaymentPlanDTO> page = managementFirmPaymentPlanCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmPaymentPlanDTO>> getAllRealEstateAssestPaymentPlan(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all real estate assest Payment Plan, page: {}", pageable.getPageNumber());
        Page<ManagementFirmPaymentPlanDTO> page = managementFirmPaymentPlanService.getAllRealEstateAssestPaymentPlan(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmPaymentPlanDTO> saveRealEstateAssestPaymentPlan(
            @Valid @RequestBody ManagementFirmPaymentPlanDTO dto) {
        log.info("Creating new real estate assest Payment Plan");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate assests Payment Plan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmPaymentPlanDTO saved = managementFirmPaymentPlanService.saveRealEstateAssestPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> getRealEstateAssestPaymentPlanById(@PathVariable Long id) {
        log.info("Fetching real estate assest Payment Plan with ID: {}", id);
        return managementFirmPaymentPlanService.getRealEstateAssestPaymentPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> updateRealEstateAssestPaymentPlan(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmPaymentPlanDTO dto) {
        log.info("Updating real estate assest Payment Plan with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmPaymentPlanDTO updated = managementFirmPaymentPlanService.updateRealEstateAssestPaymentPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRealEstateAssestPaymentPlanById(@PathVariable Long id) {
        log.info("Deleting real estate assest Payment Plan with ID: {}", id);
        boolean deleted = managementFirmPaymentPlanService.deleteRealEstateAssestPaymentPlanById(id);
        if (deleted) {
            return ResponseEntity.ok("RealEstateAssest Payment Plan deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("RealEstateAssest Payment Plan  deletion failed - ID: " + id);
        }
    }

    @PostMapping("/save-all")
    public ResponseEntity<ManagementFirmPaymentPlanDTO> saveAllRealEstateAssestPaymentPlan(
            @Valid @RequestBody List<ManagementFirmPaymentPlanDTO> dto) {
        log.info("Creating all new real estate assest Payment Plan");

        ManagementFirmPaymentPlanDTO saved = managementFirmPaymentPlanService.saveAllRealEstateAssestPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteRealEstateAssestPaymentPlanServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmPaymentPlan with ID: {}", id);

        boolean deleted = managementFirmPaymentPlanService.softRealEstateAssestPaymentPlanServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmPaymentPlan soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmPaymentPlan soft deletion failed - ID: " + id);
        }
    }
}
