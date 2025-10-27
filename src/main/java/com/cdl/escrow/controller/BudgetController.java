package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.BudgetCriteria;
import com.cdl.escrow.criteriaservice.BudgetCriteriaService;
import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BudgetRepository;
import com.cdl.escrow.service.BudgetService;
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
@RequestMapping("/api/v1/budget")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "budget-controller", description = "APIs for managing budgets")
public class BudgetController {

    private final BudgetService budgetService;

    private final BudgetCriteriaService budgetCriteriaService;

    private final BudgetRepository repository;

    private static final String ENTITY_NAME = "BUDGET";

    @GetMapping
    public ResponseEntity<Page<BudgetDTO>> getAllBudgetByCriteria(@ParameterObject BudgetCriteria criteria,
                                                                         @ParameterObject Pageable pageable) {
        Page<BudgetDTO> page = budgetCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BudgetDTO>> getAllBudgets(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all budgets , page: {}", pageable.getPageNumber());
        Page<BudgetDTO> page = budgetService.getAllBudget(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BudgetDTO> saveBudget(
            @Valid @RequestBody BudgetDTO dto) {
        log.info("Creating new Budget");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Budget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BudgetDTO saved = budgetService.saveBudget(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDTO> getBudgetById(@PathVariable Long id) {
        log.info("Fetching budget with ID: {}", id);
        return budgetService.getBudgetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("Budget not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDTO> updateBudget(
            @PathVariable Long id,
            @Valid @RequestBody BudgetDTO dto) {
        log.info("Updating Budget with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BudgetDTO updated = budgetService.updateBudget(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudgetById(@PathVariable Long id) {
        log.info("Deleting Budget with ID: {}", id);
        boolean deleted = budgetService.deleteBudgetById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBudgetServiceById(@PathVariable Long id) {
        log.info("Soft deleting Budget with ID: {}", id);

        boolean deleted = budgetService.softBudgetServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget soft deletion failed - ID: " + id);
        }
    }

    //Get api for all data

    @GetMapping("/data/{id}")
    public ResponseEntity<BudgetDTO> getBudgetDataById(@PathVariable Long id) {
        log.info("Fetching budget data with ID: {}", id);
        return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<BudgetDTO> uploadBudgetTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload budget");

        return null;
    }
}
