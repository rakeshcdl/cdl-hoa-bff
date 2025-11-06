package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.OwnerRegistryUnitTypeCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryUnitTypeCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryUnitTypeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryUnitTypeRepository;
import com.cdl.escrow.service.OwnerRegistryUnitTypeService;
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
@RequestMapping("/api/v1/owner-registry-unit-type")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-unit-type-controller", description = "APIs for owner registry unit type")
public class OwnerRegistryUnitTypeController {

    private final OwnerRegistryUnitTypeService ownerRegistryUnitTypeService;

    private final OwnerRegistryUnitTypeCriteriaService ownerRegistryUnitTypeCriteriaService;

    private final OwnerRegistryUnitTypeRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_UNIT_TYPE";

    @GetMapping
    public ResponseEntity<Page<OwnerRegistryUnitTypeDTO>> getAllOwnerRegistryUnitTypesByCriteria(@ParameterObject OwnerRegistryUnitTypeCriteria criteria,
                                                                                                  @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryUnitTypeDTO> page = ownerRegistryUnitTypeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryUnitTypeDTO>> getAllOwnerRegistryUnitTypes(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry unit type, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitTypeDTO> page = ownerRegistryUnitTypeService.getAllOwnerRegistryUnitType(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryUnitTypeDTO> saveOwnerRegistryUnitType(
            @Valid @RequestBody OwnerRegistryUnitTypeDTO dto) {
        log.info("Creating new Owner Registry unit type");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry unit type cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryUnitTypeDTO saved = ownerRegistryUnitTypeService.saveOwnerRegistryUnitType(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitTypeDTO> getOwnerRegistryUnitTypeById(@PathVariable Long id) {
        log.info("Fetching Owner Registry unit type with ID: {}", id);
        return ownerRegistryUnitTypeService.getOwnerRegistryUnitTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner unit type not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitTypeDTO> updateOwnerRegistryUnitType(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryUnitTypeDTO dto) {
        log.info("Updating Owner Registry unit type with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryUnitTypeDTO updated = ownerRegistryUnitTypeService.updateOwnerRegistryUnitType(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryUnitTypeById(@PathVariable Long id) {
        log.info("Deleting Owner Registry unit type with ID: {}", id);
        boolean deleted = ownerRegistryUnitTypeService.deleteOwnerRegistryUnitTypeById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitType deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitType deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryUnitTypeServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryUnitType with ID: {}", id);

        boolean deleted = ownerRegistryUnitTypeService.softOwnerRegistryUnitTypeServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitType soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitType soft deletion failed - ID: " + id);
        }
    }
}
