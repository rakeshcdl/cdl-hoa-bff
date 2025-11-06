package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.ManagementFirmCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmCriteriaService;
import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmRepository;
import com.cdl.escrow.service.ManagementFirmService;
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
@RequestMapping("/api/v1/management-firms")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-controller", description = "APIs for management firms")
public class ManagementFirmController {

     private final ManagementFirmService managementFirmService;

     private final ManagementFirmCriteriaService managementFirmCriteriaService;

    private final ManagementFirmRepository repository;

    private static final String ENTITY_NAME = "REAL_ESTATE_ASSEST";

    @GetMapping
    public ResponseEntity<List<ManagementFirmDTO>> getAllRealEstateAssestByCriteria(@ParameterObject ManagementFirmCriteria criteria,
                                                                                    @ParameterObject  Pageable pageable) {
        Page<ManagementFirmDTO> page = managementFirmCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmDTO>> getAllRealEstateAssest(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all real estate assest, page: {}", pageable.getPageNumber());
        Page<ManagementFirmDTO> page = managementFirmService.getAllRealEstateAssest(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmDTO> saveRealEstateAssest(
            @Valid @RequestBody ManagementFirmDTO dto) {
        log.info("Creating new real estate assest ");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate assests cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmDTO saved = managementFirmService.saveRealEstateAssest(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmDTO> getRealEstateAssestById(@PathVariable Long id) {
        log.info("Fetching real estate assest with ID: {}", id);
        return managementFirmService.getRealEstateAssestById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmDTO> updateRealEstateAssest(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmDTO dto) {
        log.info("Updating real estate assest with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmDTO updated = managementFirmService.updateRealEstateAssest(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRealEstateAssestById(@PathVariable Long id) {
        log.info("Deleting real estate assest with ID: {}", id);
        boolean deleted = managementFirmService.deleteRealEstateAssestById(id);
        if (deleted) {
            return ResponseEntity.ok("RealEstateAssest deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("RealEstateAssest deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteRealEstateAssestServiceById(@PathVariable Long id) {
        log.info("Soft deleting RealEstateAssest with ID: {}", id);

        boolean deleted = managementFirmService.softRealEstateAssestServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("RealEstateAssest soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("RealEstateAssest soft deletion failed - ID: " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ManagementFirmDTO> uploadRealEstateAssestTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload build RealEstate Assest");

       /* if (dto.getId() != null) {
            throw new BadRequestAlertException("A new build partner cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
        // BuildPartnerDTO saved = buildPartnerService.saveBuildPartner(dto);
        // return ResponseEntity.ok(saved);
        return null;
    }
}
