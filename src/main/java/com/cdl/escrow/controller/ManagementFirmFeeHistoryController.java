package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.ManagementFirmFeeHistoryCriteria;
import com.cdl.escrow.criteriaservice.ManagementFirmFeeHistoryCriteriaService;
import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.ManagementFirmFeeHistoryRepository;
import com.cdl.escrow.service.ManagementFirmFeeHistoryService;
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

import java.net.URISyntaxException;
import java.util.Objects;


@RestController
@RequestMapping("/api/v1/management-firms-fee-history")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "management-firms-fees-history-controller", description = "APIs for management firms fee history")
public class ManagementFirmFeeHistoryController {

    private final ManagementFirmFeeHistoryService managementFirmFeeHistoryService;

    private final ManagementFirmFeeHistoryCriteriaService managementFirmFeeHistoryCriteriaService;

    private final ManagementFirmFeeHistoryRepository repository;

    private static final String ENTITY_NAME = "MANAGEMENT_FIRM_FEE_HISTORY";

    @GetMapping
    public ResponseEntity<Page<ManagementFirmHistoryDTO>> getAllManagementFirmFeeHistoryByCriteria(@ParameterObject ManagementFirmFeeHistoryCriteria criteria,
                                                                                                     @ParameterObject  Pageable pageable) {
        Page<ManagementFirmHistoryDTO> page = managementFirmFeeHistoryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<ManagementFirmHistoryDTO>> getAllManagementFirmFeeHistory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Management Firm fee history, page: {}", pageable.getPageNumber());
        Page<ManagementFirmHistoryDTO> page = managementFirmFeeHistoryService.getAllManagementFirmFeeHistory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<ManagementFirmHistoryDTO> saveManagementFirmFeeHistory(
            @Valid @RequestBody ManagementFirmHistoryDTO dto) {
        log.info("Creating new Management Firm fee history");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new real estate fee history cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagementFirmHistoryDTO saved = managementFirmFeeHistoryService.saveManagementFirmFeeHistory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagementFirmHistoryDTO> getManagementFirmFeeHistoryById(@PathVariable Long id) {
        log.info("Fetching Management Firm fee history with ID: {}", id);
        return managementFirmFeeHistoryService.getManagementFirmFeeHistoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Real estate assest fee history not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagementFirmHistoryDTO> updateManagementFirmFeeHistory(
            @PathVariable Long id,
            @Valid @RequestBody ManagementFirmHistoryDTO dto) {
        log.info("Updating Management Firm fee history with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        ManagementFirmHistoryDTO updated = managementFirmFeeHistoryService.updateManagementFirmFeeHistory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteManagementFirmFeeHistoryById(@PathVariable Long id) {
        log.info("Deleting Management Firm fee history with ID: {}", id);
        boolean deleted = managementFirmFeeHistoryService.deleteManagementFirmFeeHistoryById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmHistory deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmHistory deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteManagementFirmFeeHistoryServiceById(@PathVariable Long id) {
        log.info("Soft deleting ManagementFirmHistory with ID: {}", id);

        boolean deleted = managementFirmFeeHistoryService.softManagementFirmFeeHistoryServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("ManagementFirmHistory soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("ManagementFirmHistory soft deletion failed - ID: " + id);
        }
    }


    @PutMapping("/fee-repush/{id}")
    public ResponseEntity<ManagementFirmHistoryDTO> realEstateAssestFeeHistoryFeeRepush(@PathVariable(value = "id", required = false) final Long id)
            throws URISyntaxException {
        log.debug("REST request to update ProjectFeeHistory fee repush: {}", id);
      /*  ProjectFeeHistoryDTO response = null;
        LongFilter projectHIdFilter = new LongFilter();
        projectHIdFilter.setEquals(id);
        ProjectFeeHistoryCriteriaExt projectFeeHCriteria = new ProjectFeeHistoryCriteriaExt();
        projectFeeHCriteria.setId(projectHIdFilter);
        List<ProjectFeeHistoryDTO> projectFeeHDtos = this.projectFeeHistoryQueryServiceExt
                .findByCriteria(projectFeeHCriteria);
        if (projectFeeHDtos.size() > 0) {
            ProjectFeeHistoryDTO projectFeeHDto = projectFeeHDtos.get(0);
            Gson g = new Gson();
            VatCalculationDTO vatDto = g.fromJson(projectFeeHDto.getFeeRequestBody(), VatCalculationDTO.class);

            String narration = vatDto.getTransactions().get(0).getNarrationDetails().get(0).getValue();
            response = bankIntegrationService.feeCharges(projectFeeHDto.getProjectFee(), projectFeeHDto,
                    projectFeeHDto.getProjectFee().getProjectFeeCategory().getOptionValue(),narration,null);
        }*/
        return ResponseEntity.ok(null);
    }
}
