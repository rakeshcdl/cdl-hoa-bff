package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.OwnerRegistryUnitPurchaseCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryUnitPurchaseCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryUnitPurchaseDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryUnitPurchaseRepository;
import com.cdl.escrow.service.OwnerRegistryUnitPurchaseService;
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
@RequestMapping("/api/v1/owner-registry-unit-purchase")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-unit-purchase-controller", description = "APIs for owner registry unit purchase")
public class OwnerRegistryUnitPurchaseController {

    private final OwnerRegistryUnitPurchaseService ownerRegistryUnitPurchaseService;

    private final OwnerRegistryUnitPurchaseCriteriaService ownerRegistryUnitPurchaseCriteriaService;

    private final OwnerRegistryUnitPurchaseRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_UNIT_PURCHASE";

    @GetMapping
    public ResponseEntity<List<OwnerRegistryUnitPurchaseDTO>> getAllOwnerRegistryUnitPurchaseByCriteria(@ParameterObject OwnerRegistryUnitPurchaseCriteria criteria,
                                                                                                         @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryUnitPurchaseDTO> page = ownerRegistryUnitPurchaseCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryUnitPurchaseDTO>> getAllOwnerRegistryUnitPurchase(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry unit purchase, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitPurchaseDTO> page = ownerRegistryUnitPurchaseService.getAllOwnerRegistryUnitPurchase(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryUnitPurchaseDTO> saveOwnerRegistryUnitPurchase(
            @Valid @RequestBody OwnerRegistryUnitPurchaseDTO dto) {
        log.info("Creating new Owner Registry unit purchase");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry unit purchase cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryUnitPurchaseDTO saved = ownerRegistryUnitPurchaseService.saveOwnerRegistryUnitPurchase(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitPurchaseDTO> getOwnerRegistryUnitPurchaseById(@PathVariable Long id) {
        log.info("Fetching Owner Registry unit purchase with ID: {}", id);
        return ownerRegistryUnitPurchaseService.getOwnerRegistryUnitPurchaseById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner unit purchase not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitPurchaseDTO> updateOwnerRegistryUnitPurchase(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryUnitPurchaseDTO dto) {
        log.info("Updating Owner Registry unit purchase with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryUnitPurchaseDTO updated = ownerRegistryUnitPurchaseService.updateOwnerRegistryUnitPurchase(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryUnitPurchaseById(@PathVariable Long id) {
        log.info("Deleting Owner Registry purchase unit with ID: {}", id);
        boolean deleted = ownerRegistryUnitPurchaseService.deleteOwnerRegistryUnitPurchaseById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitPurchase deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitPurchase deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryUnitPurchaseServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryUnitPurchase with ID: {}", id);

        boolean deleted = ownerRegistryUnitPurchaseService.softOwnerRegistryUnitPurchaseServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitPurchase soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitPurchase soft deletion failed - ID: " + id);
        }
    }

}
