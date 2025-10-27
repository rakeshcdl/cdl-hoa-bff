package com.cdl.escrow.controller;

import com.cdl.escrow.criteria.BudgetCategoryCriteria;
import com.cdl.escrow.criteriaservice.BudgetCategoryCriteriaService;
import com.cdl.escrow.dto.BudgetCategoryDTO;
import com.cdl.escrow.exception.BadRequestAlertException;
import com.cdl.escrow.helper.PaginationUtil;
import com.cdl.escrow.repository.BudgetCategoryRepository;
import com.cdl.escrow.service.BudgetCategoryService;
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
@RequestMapping("/api/v1/budget-category")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "budget-category-controller", description = "APIs for managing budgets category")
public class BudgetCategoryController {

    private final BudgetCategoryService budgetCategoryService;

    private final BudgetCategoryCriteriaService budgetCategoryCriteriaService;

    private final BudgetCategoryRepository repository;

    private static final String ENTITY_NAME = "BUDGET";


    @GetMapping
    public ResponseEntity<Page<BudgetCategoryDTO>> getAllBudgetCategoryByCriteria(@ParameterObject BudgetCategoryCriteria criteria,
                                                                          @ParameterObject Pageable pageable) {
        Page<BudgetCategoryDTO> page = budgetCategoryCriteriaService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/find-all")
    public ResponseEntity<Page<BudgetCategoryDTO>> getAllBudgetCategory(
            @ParameterObject @PageableDefault(size = 20) Pageable pageable) {
        log.info("Fetching all Budget Category , page: {}", pageable.getPageNumber());
        Page<BudgetCategoryDTO> page = budgetCategoryService.getAllBudgetCategory(pageable);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    public ResponseEntity<BudgetCategoryDTO> saveBudgetCategory(
            @Valid @RequestBody BudgetCategoryDTO dto) {
        log.info("Creating new Budget Category");

        if (dto.getId() != null) {
            throw new BadRequestAlertException("A new Budget Category cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BudgetCategoryDTO saved = budgetCategoryService.saveBudgetCategory(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetCategoryDTO> getBudgetCategoryById(@PathVariable Long id) {
        log.info("Fetching Budget Category with ID: {}", id);
        return budgetCategoryService.getBudgetCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    log.warn("BBudget Category  not found for ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetCategoryDTO> updateBudgetCategory(
            @PathVariable Long id,
            @Valid @RequestBody BudgetCategoryDTO dto) {
        log.info("Updating Budget Category  with ID: {}", id);
        if (dto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dto.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!repository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        BudgetCategoryDTO updated = budgetCategoryService.updateBudgetCategory(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudgetCategoryById(@PathVariable Long id) {
        log.info("Deleting Budget Category  with ID: {}", id);
        boolean deleted = budgetCategoryService.deleteBudgetCategoryById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget Category  deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget Category  deletion failed - ID: " + id);
        }
    }

    @DeleteMapping("/soft/{id}")
    public ResponseEntity<String> softDeleteBudgetCategoryServiceById(@PathVariable Long id) {
        log.info("Soft deleting Budget with ID: {}", id);

        boolean deleted = budgetCategoryService.softBudgetCategoryServiceById(id);
        if (deleted) {
            return ResponseEntity.ok("Budget Category  soft deleted - ID: " + id);
        } else {
            return ResponseEntity.badRequest().body("Budget Category  soft deletion failed - ID: " + id);
        }
    }

    //Get api for all data

    @GetMapping("/data/{id}")
    public ResponseEntity<BudgetCategoryDTO> getBudgetCategoryDataById(@PathVariable Long id) {
        log.info("Fetching Budget Category data with ID: {}", id);
        return null;
    }

    @PostMapping("/upload")
    public ResponseEntity<BudgetCategoryDTO> uploadBudgetCategoryTemplate(
            @RequestParam("file") MultipartFile file) {
        log.info("Bulk upload Budget Category ");

        return null;
    }
}
