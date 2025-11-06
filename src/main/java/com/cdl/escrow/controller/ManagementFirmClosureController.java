package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.ManagementFirmClosureCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmClosureCriteriaService;
import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmClosureRepository;
import com.cdl.escrow.service.ManagementFirmClosureService;
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
@RequestMapping("/api/v1/management-firms-closure")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-closure-controller", description = "APIs for management firms closure")
public class ManagementFirmClosureController {

   private final ManagementFirmClosureService managementFirmClosureService;

   private final ManagementFirmClosureCriteriaService managementFirmClosureCriteriaService;

    private final ManagementFirmClosureRepository repository;

    private static final String ENTITY_NAME = "REAL_ESTATE_ASSEST_CLOSURE";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmClosureDTO>> getAllRealEstateAssestClosureByCriteria(@ParameterObject ManagementFirmClosureCriteria criteria,
                                                                                                  @ParameterObject  Pageable pageable) {
        Page<ManagementFirmClosureDTO> page = managementFirmClosureCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmClosureDTO>> getAllRealEstateAssestClosure(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all real estate assest closure, page: {}", pageable.getPageNumber());
        Page<ManagementFirmClosureDTO> page = managementFirmClosureService.getAllRealEstateAssestClosure(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmClosureDTO> saveRealEstateAssestClosure(
            @Valid @RequestBody ManagementFirmClosureDTO dto) {
        log.info("Creating new real estate assest closure");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate closure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmClosureDTO saved = managementFirmClosureService.saveRealEstateAssestClosure(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmClosureDTO> getRealEstateAssestClosureById(@PathVariable Long id) {
        log.info("Fetching real estate assest closure with ID: {}", id);
        return managementFirmClosureService.getRealEstateAssestClosureById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest closure not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmClosureDTO> updateRealEstateAssestClosure(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmClosureDTO dto) {
        log.info("Updating real estate assest closure with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmClosureDTO updated = managementFirmClosureService.updateRealEstateAssestClosure(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRealEstateAssestClosureById(@PathVariable Long id) {
        log.info("Deleting real estate assest closure with ID: {}", id);
        boolean deleted = managementFirmClosureService.deleteRealEstateAssestClosureById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmClosure deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmClosure deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteRealEstateAssestClosureServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmClosure with ID: {}", id);

        boolean deleted = managementFirmClosureService.softRealEstateAssestClosureServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmClosure soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmClosure soft deletion failed - ID: " + id);
        }
    }

}
