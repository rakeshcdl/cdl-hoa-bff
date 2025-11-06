package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.AssetRegisterContactCriteria;
import com.cdl.escrow.criteriaservice.AssetRegisterContactCriteriaService;
import com.cdl.escrow.dto.AssetRegisterContactDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.AssetRegisterContactRepository;
import com.cdl.escrow.service.AssetRegisterContactService;
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
@RequestMapping("/api/v1/asset-register-contact")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "asset-register-contact-controller", description = "APIs for managing asset register contact")
public class AssetRegisterContactController {

   private final AssetRegisterContactService assetRegisterContactService;

   private final AssetRegisterContactCriteriaService assetRegisterContactCriteriaService;

    private final AssetRegisterContactRepository repository;

    private static final String ENTITY_NAME = "ASSET_REGISTER_CONTACT";

    @GetMapping
    public ResponseEntity<Page<AssetRegisterContactDTO>> getAllAssetRegisterContactsByCriteria(@ParameterObject AssetRegisterContactCriteria criteria,
                                                                                              @ParameterObject  Pageable pageable) {
        Page<AssetRegisterContactDTO> page = assetRegisterContactCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }


    @GetMapping("/find-all")
    public ResponseEntity<Page<AssetRegisterContactDTO>> getAllAssetRegisterContacts(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Asset Register contact , page: {}", pageable.getPageNumber());
        Page<AssetRegisterContactDTO> page = assetRegisterContactService.getAllAssetRegisterContact(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AssetRegisterContactDTO> saveAssetRegisterContact(
            @Valid @RequestBody AssetRegisterContactDTO dto) {
        log.info("Creating new Asset Register contact");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Asset Register contact cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetRegisterContactDTO saved = assetRegisterContactService.saveAssetRegisterContact(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetRegisterContactDTO> getAssetRegisterContactById(@PathVariable Long id) {
        log.info("Fetching Asset Register contact with ID: {}", id);
        return assetRegisterContactService.getAssetRegisterContactById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Build partner contact not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetRegisterContactDTO> updateAssetRegisterContact(
            @PathVariable Long id,
            @Valid @RequestBody AssetRegisterContactDTO dto) {
        log.info("Updating Asset Register contact with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AssetRegisterContactDTO updated = assetRegisterContactService.updateAssetRegisterContact(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssetRegisterContactById(@PathVariable Long id) {
        log.info("Deleting Asset Register contact with ID: {}", id);
        boolean deleted = assetRegisterContactService.deleteAssetRegisterContactById(id);
        if (deleted) {
            return ResponseEntity.ok("AssetRegisterContact deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AssetRegisterContact deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAssetRegisterContactServiceById(@PathVariable Long id) {
        log.info("Soft deleting AssetRegisterContact with ID: {}", id);

        boolean deleted = assetRegisterContactService.softAssetRegisterContactServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("AssetRegisterContact soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AssetRegisterContact soft deletion failed - ID: " + id);
        }
    }
}
