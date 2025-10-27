package com.cdl.escrow.service;

import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetService {

    Page<BudgetDTO> getAllBudget(final Pageable pageable);

    Optional<BudgetDTO> getBudgetById(Long id);

    BudgetDTO saveBudget(BudgetDTO budgetDTO);

    BudgetDTO updateBudget(Long id, BudgetDTO budgetDTO);

    Boolean deleteBudgetById(Long id);

    void finalizeBudget(Long moduleId, TaskStatus status);

    boolean softBudgetServiceById(Long id);

}
