package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.OwnerRegistryCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryRepository;
import com.cdl.escrow.service.OwnerRegistryService;
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

import java.util.Objects;


@RestController
@RequestMapping("/api/v1/owner-registry")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-controller", description = "APIs for owner registry")
public class OwnerRegistryController {

    private final OwnerRegistryService ownerRegistryService;

    private final OwnerRegistryCriteriaService ownerRegistryCriteriaService;

    private final OwnerRegistryRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY";

    @GetMapping
    public ResponseEntity<Page<OwnerRegistryDTO>> getAllOwnerRegistryByCriteria(@ParameterObject OwnerRegistryCriteria criteria,
                                                                                  @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryDTO> page = ownerRegistryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryDTO>> getAllOwnerRegistry(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryDTO> page = ownerRegistryService.getAllOwnerRegistry(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryDTO> saveOwnerRegistry(
            @Valid @RequestBody OwnerRegistryDTO dto) {
        log.info("Creating new Owner Registry");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryDTO saved = ownerRegistryService.saveOwnerRegistry(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryDTO> getOwnerRegistryById(@PathVariable Long id) {
        log.info("Fetching Owner Registry with ID: {}", id);
        return ownerRegistryService.getOwnerRegistryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryDTO> updateOwnerRegistry(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryDTO dto) {
        log.info("Updating Owner Registry with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryDTO updated = ownerRegistryService.updateOwnerRegistry(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryById(@PathVariable Long id) {
        log.info("Deleting Owner Registry with ID: {}", id);
        boolean deleted = ownerRegistryService.deleteOwnerRegistryById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistry deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistry deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistry with ID: {}", id);

        boolean deleted = ownerRegistryService.softOwnerRegistryServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistry soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistry soft deletion failed - ID: " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<OwnerRegistryDTO> uploadOwnerRegistryTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload build Owner Registry");

       /* if (dto.getId() != null) {
            throw new BadRequestAlertException("A new build partner cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
        // BuildPartnerDTO saved = buildPartnerService.saveBuildPartner(dto);
        // return ResponseEntity.ok(saved);
        return null;
    }
}
