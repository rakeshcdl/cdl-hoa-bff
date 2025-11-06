package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.AssetRegisterCriteria;
import com.cdl.escrow.criteriaservice.AssetRegisterCriteriaService;
import com.cdl.escrow.dto.AssetRegisterDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.AssetRegisterRepository;
import com.cdl.escrow.service.AssetRegisterService;
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
@RequestMapping("/api/v1/asset-register")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "asset-register-controller", description = "APIs for managing asset register")
public class AssetRegisterController {

    private final AssetRegisterService assetRegisterService;

    private final AssetRegisterCriteriaService buildPartnerCriteriaService;

    private final AssetRegisterRepository repository;

    private static final String ENTITY_NAME = "ASSET_REGISTER";

    @GetMapping
    public ResponseEntity<List<AssetRegisterDTO>> getAllAssetRegistersByCriteria(@ParameterObject AssetRegisterCriteria criteria,
                                                                                @ParameterObject  Pageable pageable) {
        Page<AssetRegisterDTO> page = buildPartnerCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<AssetRegisterDTO>> getAllAssetRegisters(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Asset Register , page: {}", pageable.getPageNumber());
        Page<AssetRegisterDTO> page = assetRegisterService.getAllAssetRegister(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<AssetRegisterDTO> saveAssetRegister(
            @Valid @RequestBody AssetRegisterDTO dto) {
        log.info("Creating new Asset Register");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Asset Register cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetRegisterDTO saved = assetRegisterService.saveAssetRegister(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetRegisterDTO> getAssetRegisterById(@PathVariable Long id) {
        log.info("Fetching Asset Register with ID: {}", id);
        return assetRegisterService.getAssetRegisterById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Build partner not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetRegisterDTO> updateAssetRegister(
            @PathVariable Long id,
            @Valid @RequestBody AssetRegisterDTO dto) {
        log.info("Updating Asset Register with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        AssetRegisterDTO updated = assetRegisterService.updateAssetRegister(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAssetRegisterById(@PathVariable Long id) {
        log.info("Deleting Asset Register with ID: {}", id);
        boolean deleted = assetRegisterService.deleteAssetRegisterById(id);
        if (deleted) {
            return ResponseEntity.ok("AssetRegister deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AssetRegister deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteAssetRegisterServiceById(@PathVariable Long id) {
        log.info("Soft deleting AssetRegister with ID: {}", id);

        boolean deleted = assetRegisterService.softAssetRegisterContactServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("AssetRegister soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("AssetRegister soft deletion failed - ID: " + id);
        }
    }

    //Get api for all data

    @GetMapping("/data/{id}")
    public ResponseEntity<AssetRegisterDTO> getAssetRegisterDataById(@PathVariable Long id) {
        log.info("Fetching Asset Register data with ID: {}", id);
       return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<AssetRegisterDTO> uploadAssetRegisterTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload Asset Register");

       /* if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Asset Register cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
       // AssetRegisterDTO saved = buildPartnerService.saveAssetRegister(dto);
       // return ResponseEntity.ok(saved);
        return null;
    }
}
