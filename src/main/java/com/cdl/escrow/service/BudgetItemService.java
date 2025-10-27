package com.cdl.escrow.service;

import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetItemService {

    Page<BudgetItemDTO> getAllBudgetItem(final Pageable pageable);

    Optional<BudgetItemDTO> getBudgetItemById(Long id);

    BudgetItemDTO saveBudgetItem(BudgetItemDTO budgetItemDTO);

    BudgetItemDTO updateBudgetItem(Long id, BudgetItemDTO budgetItemDTO);

    Boolean deleteBudgetItemById(Long id);

    void finalizeBudgetItem(Long moduleId, TaskStatus status);

    boolean softBudgetItemServiceById(Long id);
}
