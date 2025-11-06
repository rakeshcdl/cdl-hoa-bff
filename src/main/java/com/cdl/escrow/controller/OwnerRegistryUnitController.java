package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.OwnerRegistryUnitCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryUnitCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryUnitRepository;
import com.cdl.escrow.service.OwnerRegistryUnitService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1/owner-registry-unit")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-unit-controller", description = "APIs for owner registry unit")
public class OwnerRegistryUnitController {

   private final OwnerRegistryUnitService ownerRegistryUnitService;

   private final OwnerRegistryUnitCriteriaService ownerRegistryUnitCriteriaService;

    private final OwnerRegistryUnitRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_UNIT";

    @GetMapping
    public ResponseEntity<List<OwnerRegistryUnitDTO>> getAllOwnerRegistryUnitsByCriteria(@ParameterObject OwnerRegistryUnitCriteria criteria,
                                                                                          @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryUnitDTO> page = ownerRegistryUnitCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryUnitDTO>> getAllOwnerRegistryUnits(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry unit, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitDTO> page = ownerRegistryUnitService.getAllOwnerRegistryUnit(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryUnitDTO> saveOwnerRegistryUnit(
            @Valid @RequestBody OwnerRegistryUnitDTO dto) {
        log.info("Creating new Owner Registry unit ");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry unit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryUnitDTO saved = ownerRegistryUnitService.saveOwnerRegistryUnit(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitDTO> getOwnerRegistryUnitById(@PathVariable Long id) {
        log.info("Fetching Owner Registryunit  with ID: {}", id);
        return ownerRegistryUnitService.getOwnerRegistryUnitById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner unit  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitDTO> updateOwnerRegistryUnit(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryUnitDTO dto) {
        log.info("Updating Owner Registry unit with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryUnitDTO updated = ownerRegistryUnitService.updateOwnerRegistryUnit(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryUnitById(@PathVariable Long id) {
        log.info("Deleting Owner Registryunit with ID: {}", id);
        boolean deleted = ownerRegistryUnitService.deleteOwnerRegistryUnitById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnit deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnit deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryUnitServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryUnit with ID: {}", id);

        boolean deleted = ownerRegistryUnitService.softOwnerRegistryUnitServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnit soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnit soft deletion failed - ID: " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<OwnerRegistryUnitDTO> uploadOwnerRegistryTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload build Owner RegistryUnit");

       /* if (dto.getId() != null) {
            throw new BadRequestAlertException("A new build partner cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
        // BuildPartnerDTO saved = buildPartnerService.saveBuildPartner(dto);
        // return ResponseEntity.ok(saved);
        return null;
    }


}
