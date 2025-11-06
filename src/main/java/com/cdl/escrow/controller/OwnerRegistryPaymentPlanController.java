package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.OwnerRegistryPaymentPlanCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryPaymentPlanCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryPaymentPlanDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryPaymentPlanRepository;
import com.cdl.escrow.service.OwnerRegistryPaymentPlanService;
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
@RequestMapping("/api/v1/owner-registry-payment-plan")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-payment-plan-controller", description = "APIs for owner registry payment plan")
public class OwnerRegistryPaymentPlanController {

    private final OwnerRegistryPaymentPlanService ownerRegistryPaymentPlanService;

    private final OwnerRegistryPaymentPlanRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_PAYMENT_PLAN";

    private final OwnerRegistryPaymentPlanCriteriaService ownerRegistryPaymentPlanCriteriaService;


    @GetMapping
    public ResponseEntity<List<OwnerRegistryPaymentPlanDTO>> getAllOwnerRegistryPaymentPlanByCriteria(@ParameterObject OwnerRegistryPaymentPlanCriteria criteria,
                                                                                                       @ParameterObject Pageable pageable) {
        Page<OwnerRegistryPaymentPlanDTO> page = ownerRegistryPaymentPlanCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryPaymentPlanDTO>> getAllOwnerRegistryPaymentPlan(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry Payment Plan, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryPaymentPlanDTO> page = ownerRegistryPaymentPlanService.getAllOwnerRegistryPaymentPlan(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryPaymentPlanDTO> saveOwnerRegistryPaymentPlan(
            @Valid @RequestBody OwnerRegistryPaymentPlanDTO dto) {
        log.info("Creating new Owner Registry Payment Plan");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry Payment Plan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryPaymentPlanDTO saved = ownerRegistryPaymentPlanService.saveOwnerRegistryPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryPaymentPlanDTO> getOwnerRegistryPaymentPlanById(@PathVariable Long id) {
        log.info("Fetching Owner Registry Payment Plan with ID: {}", id);
        return ownerRegistryPaymentPlanService.getROwnerRegistryPaymentPlanById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryPaymentPlanDTO> updateOwnerRegistryPaymentPlan(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryPaymentPlanDTO dto) {
        log.info("Updating Owner Registry Payment Plan with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryPaymentPlanDTO updated = ownerRegistryPaymentPlanService.updateOwnerRegistryPaymentPlan(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryPaymentPlanById(@PathVariable Long id) {
        log.info("Deleting Owner Registry Payment Plan with ID: {}", id);
        boolean deleted = ownerRegistryPaymentPlanService.deleteOwnerRegistryPaymentPlanById(id);
        if (deleted) {
            return ResponseEntity.ok("Owner Registry Payment Plan deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Owner Registry Payment Plan  deletion failed - ID: " + id);
        }
    }

    @PostMapping("/save-all")
    public ResponseEntity<OwnerRegistryPaymentPlanDTO> saveAllOwnerRegistryPaymentPlan(
            @Valid @RequestBody List<OwnerRegistryPaymentPlanDTO> dto) {
        log.info("Creating all new Owner Registry Payment Plan");

        OwnerRegistryPaymentPlanDTO saved = ownerRegistryPaymentPlanService.saveAllOwnerRegistryPaymentPlan(dto);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryPaymentPlanServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryPaymentPlan with ID: {}", id);

        boolean deleted = ownerRegistryPaymentPlanService.softOwnerRegistryPaymentPlanServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryPaymentPlan soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryPaymentPlan soft deletion failed - ID: " + id);
        }
    }
}
