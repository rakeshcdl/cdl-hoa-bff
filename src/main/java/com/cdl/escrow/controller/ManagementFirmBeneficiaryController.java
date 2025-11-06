package com.cdl.escrow.controller;



import com.cdl.escrow.criteria.ManagementFirmBeneficiaryCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmBeneficiaryCriteriaService;
import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmBeneficiaryRepository;
import com.cdl.escrow.service.ManagementFirmBeneficiaryService;
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
@RequestMapping("/api/v1/management-firms-beneficiary")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-beneficiary-controller", description = "APIs for management firms beneficiary")
public class ManagementFirmBeneficiaryController {
    private final ManagementFirmBeneficiaryService managementFirmBeneficiaryService;

    private final ManagementFirmBeneficiaryCriteriaService managementFirmBeneficiaryCriteriaService;

    private final ManagementFirmBeneficiaryRepository repository;

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_BENEFICIARY";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmBeneficiaryDTO>> getAllManagementFirmBeneficiaryByCriteria(@ParameterObject ManagementFirmBeneficiaryCriteria criteria,
                                                                                                          @ParameterObject  Pageable pageable) {
        Page<ManagementFirmBeneficiaryDTO> page = managementFirmBeneficiaryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmBeneficiaryDTO>> getAllManagementFirmBeneficiary(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Management Firm beneficiary, page: {}", pageable.getPageNumber());
        Page<ManagementFirmBeneficiaryDTO> page = managementFirmBeneficiaryService.getAllManagementFirmBeneficiary(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmBeneficiaryDTO> saveManagementFirmBeneficiary(
            @Valid @RequestBody ManagementFirmBeneficiaryDTO dto) {
        log.info("Creating new Management Firm beneficiary");
        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate beneficiary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmBeneficiaryDTO saved = managementFirmBeneficiaryService.saveManagementFirmBeneficiary(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmBeneficiaryDTO> getManagementFirmBeneficiaryById(@PathVariable Long id) {
        log.info("Fetching Management Firm beneficiary with ID: {}", id);
        return managementFirmBeneficiaryService.getManagementFirmBeneficiaryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest beneficiary not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmBeneficiaryDTO> updateManagementFirmBeneficiary(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmBeneficiaryDTO dto) {
        log.info("Updating Management Firm beneficiary with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmBeneficiaryDTO updated = managementFirmBeneficiaryService.updateManagementFirmBeneficiary(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManagementFirmBeneficiaryById(@PathVariable Long id) {
        log.info("Deleting Management Firm beneficiary with ID: {}", id);
        boolean deleted = managementFirmBeneficiaryService.deleteManagementFirmBeneficiaryById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmBeneficiary deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmBeneficiary deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteManagementFirmBeneficiaryServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmBeneficiary with ID: {}", id);

        boolean deleted = managementFirmBeneficiaryService.softManagementFirmBeneficiaryServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmBeneficiary soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmBeneficiary soft deletion failed - ID: " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ManagementFirmBeneficiaryDTO> uploadManagementFirmBeneficiaryTemplate(
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
