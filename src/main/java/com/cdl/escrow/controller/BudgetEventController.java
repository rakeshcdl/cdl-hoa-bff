package com.cdl.escrow.controller;


import com.cdl.escrow.criteria.BudgetEventCriteria;
import com.cdl.escrow.criteriaservice.BudgetEventCriteriaService;
import com.cdl.escrow.dto.BudgetEventDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BudgetEventRepository;
import com.cdl.escrow.service.BudgetEventService;
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
@RequestMapping("/api/v1/budget-event")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "budget-event-controller", description = "APIs for managing budget events")
public class BudgetEventController {

    private final BudgetEventService budgetEventService;

    private final BudgetEventCriteriaService budgetEventCriteriaService;

    private final BudgetEventRepository repository;

    private static final String ENTITY_NAME = "BUDGET EVENTS";

    @GetMapping
    public ResponseEntity<Page<BudgetEventDTO>> getAllBudgetEventByCriteria(@ParameterObject BudgetEventCriteria criteria,
                                                                       @ParameterObject Pageable pageable) {
        Page<BudgetEventDTO> page = budgetEventCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BudgetEventDTO>> getAllBudgetEvents(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all budget events , page: {}", pageable.getPageNumber());
        Page<BudgetEventDTO> page = budgetEventService.getAllBudgetEvent(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BudgetEventDTO> saveBudgetEvent(
            @Valid @RequestBody BudgetEventDTO dto) {
        log.info("Creating new Budget Event");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Budget Event cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BudgetEventDTO saved = budgetEventService.saveBudgetEvent(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetEventDTO> getBudgetEventById(@PathVariable Long id) {
        log.info("Fetching budget Event with ID: {}", id);
        return budgetEventService.getBudgetEventById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Budget Event not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetEventDTO> updateBudgetEvent(
            @PathVariable Long id,
            @Valid @RequestBody BudgetEventDTO dto) {
        log.info("Updating Budget Event with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BudgetEventDTO updated = budgetEventService.updateBudgetEvent(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudgetEventById(@PathVariable Long id) {
        log.info("Deleting Budget Event with ID: {}", id);
        boolean deleted = budgetEventService.deleteBudgetEventById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget Event deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget Event deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBudgetEventServiceById(@PathVariable Long id) {
        log.info("Soft deleting Budget Event with ID: {}", id);

        boolean deleted = budgetEventService.softBudgetEventServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget Event soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget Event soft deletion failed - ID: " + id);
        }
    }

    //Get api for all data

    @GetMapping("/data/{id}")
    public ResponseEntity<BudgetEventDTO> getBBudgetEventDataById(@PathVariable Long id) {
        log.info("Fetching budget Event data with ID: {}", id);
        return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<BudgetEventDTO> uploadBudgetEventTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload budget Event");

        return null;
    }
}
