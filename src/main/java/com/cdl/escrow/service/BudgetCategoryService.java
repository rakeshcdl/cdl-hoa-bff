package com.cdl.escrow.service;

import com.cdl.escrow.dto.BudgetCategoryDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetCategoryService {

    Page<BudgetCategoryDTO> getAllBudgetCategory(final Pageable pageable);

    Optional<BudgetCategoryDTO> getBudgetCategoryById(Long id);

    BudgetCategoryDTO saveBudgetCategory(BudgetCategoryDTO budgetCategoryDTO);

    BudgetCategoryDTO updateBudgetCategory(Long id, BudgetCategoryDTO budgetCategoryDTO);

    Boolean deleteBudgetCategoryById(Long id);

    void finalizeBudgetCategory(Long moduleId, TaskStatus status);

    boolean softBudgetCategoryServiceById(Long id);
}
