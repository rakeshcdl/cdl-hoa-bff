package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.BudgetItemCriteria;
import com.cdl.escrow.criteriaservice.BudgetItemCriteriaService;
import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BudgetItemRepository;
import com.cdl.escrow.service.BudgetItemService;
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
@RequestMapping("/api/v1/budget-item")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "budget-item-controller", description = "APIs for managing budget items")
public class BudgetItemController {

    private final BudgetItemService budgetItemService;

    private final BudgetItemCriteriaService budgetItemCriteriaService;

    private final BudgetItemRepository repository;

    private static final String ENTITY_NAME = "BUDGET ITEM";

    @GetMapping
    public ResponseEntity<Page<BudgetItemDTO>> getAllBudgetByCriteria(@ParameterObject BudgetItemCriteria criteria,
                                                                      @ParameterObject Pageable pageable) {
        Page<BudgetItemDTO> page = budgetItemCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BudgetItemDTO>> getAllBudgets(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all budget items , page: {}", pageable.getPageNumber());
        Page<BudgetItemDTO> page = budgetItemService.getAllBudgetItem(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BudgetItemDTO> saveBudget(
            @Valid @RequestBody BudgetItemDTO dto) {
        log.info("Creating new Budget item");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Budget items cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BudgetItemDTO saved = budgetItemService.saveBudgetItem(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetItemDTO> getBudgetById(@PathVariable Long id) {
        log.info("Fetching budget items with ID: {}", id);
        return budgetItemService.getBudgetItemById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Budget items not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetItemDTO> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetItemDTO dto) {
        log.info("Updating Budget items with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BudgetItemDTO updated = budgetItemService.updateBudgetItem(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudgetById(@PathVariable Long id) {
        log.info("Deleting Budget items with ID: {}", id);
        boolean deleted = budgetItemService.deleteBudgetItemById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBudgetServiceById(@PathVariable Long id) {
        log.info("Soft deleting Budget items with ID: {}", id);

        boolean deleted = budgetItemService.softBudgetItemServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget items soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget items soft deletion failed - ID: " + id);
        }
    }

    //Get api for all data

    @GetMapping("/data/{id}")
    public ResponseEntity<BudgetItemDTO> getBudgetDataById(@PathVariable Long id) {
        log.info("Fetching budget items data with ID: {}", id);
        return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<BudgetItemDTO> uploadBudgetTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload budget items");

        return null;
    }

}
