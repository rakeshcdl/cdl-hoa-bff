package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.OwnerRegistryBankInfoCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryBankInfoCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryBankInfoDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryBankInfoRepository;
import com.cdl.escrow.service.OwnerRegistryBankInfoService;
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
@RequestMapping("/api/v1/owner-registry-bank-info")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-bank-info-controller", description = "APIs for owner registry bank info")
public class OwnerRegistryBankInfoController {

   private final OwnerRegistryBankInfoService ownerRegistryBankInfoService;

   private final OwnerRegistryBankInfoCriteriaService ownerRegistryBankInfoCriteriaService;

    private final OwnerRegistryBankInfoRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_BANK_INFO";

    @GetMapping
    public ResponseEntity<List<OwnerRegistryBankInfoDTO>> getAllOwnerRegistryBankInfosByCriteria(@ParameterObject OwnerRegistryBankInfoCriteria criteria,
                                                                                                  @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryBankInfoDTO> page = ownerRegistryBankInfoCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryBankInfoDTO>> getAllOwnerRegistryBankInfos(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry bank info, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryBankInfoDTO> page = ownerRegistryBankInfoService.getAllOwnerRegistryBankInfo(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryBankInfoDTO> saveOwnerRegistryBankInfo(
            @Valid @RequestBody OwnerRegistryBankInfoDTO dto) {
        log.info("Creating new Owner Registry bank infos");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry bank info cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryBankInfoDTO saved = ownerRegistryBankInfoService.saveOwnerRegistryBankInfo(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryBankInfoDTO> getOwnerRegistryBankInfoById(@PathVariable Long id) {
        log.info("Fetching Owner Registry bank info with ID: {}", id);
        return ownerRegistryBankInfoService.getOwnerRegistryBankInfoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner bank info not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryBankInfoDTO> updateOwnerRegistryBankInfo(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryBankInfoDTO dto) {
        log.info("Updating Owner Registry bank info with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryBankInfoDTO updated = ownerRegistryBankInfoService.updateOwnerRegistryBankInfo(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryBankInfoById(@PathVariable Long id) {
        log.info("Deleting Owner Registry bank info with ID: {}", id);
        boolean deleted = ownerRegistryBankInfoService.deleteOwnerRegistryBankInfoById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryBankInfo deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryBankInfo deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryBankInfoServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryBankInfo with ID: {}", id);

        boolean deleted = ownerRegistryBankInfoService.softOwnerRegistryBankInfoServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryBankInfo soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryBankInfo soft deletion failed - ID: " + id);
        }
    }
}
