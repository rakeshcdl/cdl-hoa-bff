package com.cdl.escrow.controller;

import com.cdl.escrow.dto.OwnerRegistryContactDetailDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.repository.OwnerRegistryContactDetailRepository;
import com.cdl.escrow.service.OwnerRegistryContactDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/owner-registry-contact-details")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-contact-detail-controller", description = "APIs for owner registry contact details")
public class OwnerRegistryContactDetailController {

    private final OwnerRegistryContactDetailService ownerRegistryContactDetailService;

    private final OwnerRegistryContactDetailRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_CONTACT_DETAIL";

  /*  @GetMapping
    public ResponseEntity<Page<OwnerRegistryDTO>> getAllOwnerRegistryByCriteria(@ParameterObject OwnerRegistryCriteria criteria,
                                                                                @ParameterObject Pageable pageable) {
        Page<OwnerRegistryDTO> page = ownerRegistryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }*/

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryContactDetailDTO>> getAllOwnerRegistryContactDetail(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry Contact Detail, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryContactDetailDTO> page = ownerRegistryContactDetailService.getAllOwnerRegistryContactDetail(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryContactDetailDTO> saveOwnerRegistryContactDetail(
            @Valid @RequestBody OwnerRegistryContactDetailDTO dto) {
        log.info("Creating new Owner Registry Contact Detail");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry Contact Detail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryContactDetailDTO saved = ownerRegistryContactDetailService.saveOwnerRegistryContactDetail(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryContactDetailDTO> getOwnerRegistryContactDetailById(@PathVariable Long id) {
        log.info("Fetching Owner Registry Contact Detail with ID: {}", id);
        return ownerRegistryContactDetailService.getOwnerRegistryContactDetailById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryContactDetailDTO> updateOwnerRegistryContactDetail(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryContactDetailDTO dto) {
        log.info("Updating Owner Registry Contact Detail with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryContactDetailDTO updated = ownerRegistryContactDetailService.updateOwnerRegistryContactDetail(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryContactDetailById(@PathVariable Long id) {
        log.info("Deleting Owner Registry Contact Detail with ID: {}", id);
        boolean deleted = ownerRegistryContactDetailService.deleteOwnerRegistryContactDetailById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistry deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistry deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softOwnerRegistryContactDetailServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistry with ID: {}", id);

        boolean deleted = ownerRegistryContactDetailService.softOwnerRegistryContactDetailServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistry soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistry soft deletion failed - ID: " + id);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<OwnerRegistryContactDetailDTO> uploadOwnerRegistryContactDetail(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload build Owner Registry Contact Detail");

       /* if (dto.getId() != null) {
            throw new BadRequestAlertException("A new build partner cannot already have an ID", ENTITY_NAME, "idexists");
        }*/
        // BuildPartnerDTO saved = buildPartnerService.saveBuildPartner(dto);
        // return ResponseEntity.ok(saved);
        return null;
    }
}
