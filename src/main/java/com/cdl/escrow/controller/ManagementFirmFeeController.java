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

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_FEE";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmFeeDTO>> getAllManagementFirmFeeByCriteria(@ParameterObject ManagementFirmFeeCriteria criteria,
                                                                                          @ParameterObject  Pageable pageable) {
        Page<ManagementFirmFeeDTO> page = managementFirmFeeCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmFeeDTO>> getAllManagementFirmFee(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Management Firm fee, page: {}", pageable.getPageNumber());
        Page<ManagementFirmFeeDTO> page = managementFirmFeeService.getAllManagementFirmFee(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmFeeDTO> saveManagementFirmFee(
            @Valid @RequestBody ManagementFirmFeeDTO dto) {
        log.info("Creating new Management Firm fee");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Management Firm fee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmFeeDTO saved = managementFirmFeeService.saveManagementFirmFee(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmFeeDTO> getManagementFirmFeeById(@PathVariable Long id) {
        log.info("Fetching Management Firm fee with ID: {}", id);
        return managementFirmFeeService.getManagementFirmFeeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest fee not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmFeeDTO> updateManagementFirmFee(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmFeeDTO dto) {
        log.info("Updating Management Firm fee with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmFeeDTO updated = managementFirmFeeService.updateManagementFirmFee(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManagementFirmFeeById(@PathVariable Long id) {
        log.info("Deleting Management Firm fee with ID: {}", id);
        boolean deleted = managementFirmFeeService.deleteManagementFirmFeeById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFee deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFee deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteManagementFirmFeeServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmFee with ID: {}", id);

        boolean deleted = managementFirmFeeService.softManagementFirmFeeServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmFee soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmFee soft deletion failed - ID: " + id);
        }
    }
}
