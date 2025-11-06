package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.OwnerRegistryUnitBookingCriteria;
import com.cdl.escrow.criteriaservice.OwnerRegistryUnitBookingCriteriaService;
import com.cdl.escrow.dto.OwnerRegistryUnitBookingDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.OwnerRegistryUnitBookingRepository;
import com.cdl.escrow.service.OwnerRegistryUnitBookingService;
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
@RequestMapping("/api/v1/owner-registry-unit-booking")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "owner-registry-unit-booking-controller", description = "APIs for owner registry unit booking")
public class OwnerRegistryUnitBookingController {

   private final OwnerRegistryUnitBookingService ownerRegistryUnitBookingService;

   private final OwnerRegistryUnitBookingCriteriaService ownerRegistryUnitBookingCriteriaService;

    private final OwnerRegistryUnitBookingRepository repository;

    private static final String ENTITY_NAME = "OWNER_REGISTRY_UNIT_BOOKING";

    @GetMapping
    public ResponseEntity<List<OwnerRegistryUnitBookingDTO>> getAllOwnerRegistryUnitBookingsByCriteria(@ParameterObject OwnerRegistryUnitBookingCriteria criteria,
                                                                                                        @ParameterObject  Pageable pageable) {
        Page<OwnerRegistryUnitBookingDTO> page = ownerRegistryUnitBookingCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<OwnerRegistryUnitBookingDTO>> getAllOwnerRegistryUnitBookings(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Owner Registry unit booking, page: {}", pageable.getPageNumber());
        Page<OwnerRegistryUnitBookingDTO> page = ownerRegistryUnitBookingService.getAllOwnerRegistryUnitBooking(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<OwnerRegistryUnitBookingDTO> saveOwnerRegistryUnitBooking(
            @Valid @RequestBody OwnerRegistryUnitBookingDTO dto) {
        log.info("Creating new Owner Registry unit booking");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Owner Registry unit booking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OwnerRegistryUnitBookingDTO saved = ownerRegistryUnitBookingService.saveOwnerRegistryUnitBooking(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitBookingDTO> getOwnerRegistryUnitBookingById(@PathVariable Long id) {
        log.info("Fetching Owner Registry unit booking with ID: {}", id);
        return ownerRegistryUnitBookingService.getOwnerRegistryUnitBookingById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Capital partner unit booking not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerRegistryUnitBookingDTO> updateOwnerRegistryUnitBooking(
            @PathVariable Long id,
            @Valid @RequestBody OwnerRegistryUnitBookingDTO dto) {
        log.info("Updating Owner Registry unit booking with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        OwnerRegistryUnitBookingDTO updated = ownerRegistryUnitBookingService.updateOwnerRegistryUnitBooking(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwnerRegistryUnitBookingById(@PathVariable Long id) {
        log.info("Deleting Owner Registry unit booking with ID: {}", id);
        boolean deleted = ownerRegistryUnitBookingService.deleteOwnerRegistryUnitBookingById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitBooking deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitBooking deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteOwnerRegistryUnitBookingServiceById(@PathVariable Long id) {
        log.info("Soft deleting OwnerRegistryUnitBooking with ID: {}", id);

        boolean deleted = ownerRegistryUnitBookingService.softOwnerRegistryUnitBookingServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("OwnerRegistryUnitBooking soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("OwnerRegistryUnitBooking soft deletion failed - ID: " + id);
        }
    }

}
