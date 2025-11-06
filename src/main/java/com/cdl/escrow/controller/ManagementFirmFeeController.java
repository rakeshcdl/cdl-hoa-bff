package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.ManagementFirmFeeCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmFeeCriteriaService;
import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmFeeRepository;
import com.cdl.escrow.service.ManagementFirmFeeService;
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
@RequestMapping("/api/v1/management-firms-fee")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-fees-controller", description = "APIs for management firms fees")
public class ManagementFirmFeeController {

    private final ManagementFirmFeeService managementFirmFeeService;

    private final ManagementFirmFeeCriteriaService managementFirmFeeCriteriaService;

    private final ManagementFirmFeeRepository repository;

    private static final String ENTITY_NAME = "REAL_ESTATE_FEE";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmFeeDTO>> getAllRealEstateAssestFeeByCriteria(@ParameterObject ManagementFirmFeeCriteria criteria,
                                                                                          @ParameterObject  Pageable pageable) {
        Page<ManagementFirmFeeDTO> page = managementFirmFeeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmFeeDTO>> getAllRealEstateAssestFee(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all real estate assest fee, page: {}", pageable.getPageNumber());
        Page<ManagementFirmFeeDTO> page = managementFirmFeeService.getAllRealEstateAssestFee(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmFeeDTO> saveRealEstateAssestFee(
            @Valid @RequestBody ManagementFirmFeeDTO dto) {
        log.info("Creating new real estate assest fee");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate assest fee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmFeeDTO saved = managementFirmFeeService.saveRealEstateAssestFee(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmFeeDTO> getRealEstateAssestFeeById(@PathVariable Long id) {
        log.info("Fetching real estate assest fee with ID: {}", id);
        return managementFirmFeeService.getRealEstateAssestFeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest fee not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmFeeDTO> updateRealEstateAssestFee(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmFeeDTO dto) {
        log.info("Updating real estate assest fee with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmFeeDTO updated = managementFirmFeeService.updateRealEstateAssestFee(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRealEstateAssestFeeById(@PathVariable Long id) {
        log.info("Deleting real estate assest fee with ID: {}", id);
        boolean deleted = managementFirmFeeService.deleteRealEstateAssestFeeById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFee deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFee deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteRealEstateAssestFeeServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmFee with ID: {}", id);

        boolean deleted = managementFirmFeeService.softRealEstateAssestFeeServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFee soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFee soft deletion failed - ID: " + id);
        }
    }
}
