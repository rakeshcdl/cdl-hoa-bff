package com.cdl.escrow.service;

import com.cdl.escrow.dto.BudgetEventDTO;
import com.cdl.escrow.entity.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BudgetEventService {

    Page<BudgetEventDTO> getAllBudgetEvent(final Pageable pageable);

    Optional<BudgetEventDTO> getBudgetEventById(Long id);

    BudgetEventDTO saveBudgetEvent(BudgetEventDTO budgetEventDTO);

    BudgetEventDTO updateBudgetEvent(Long id, BudgetEventDTO budgetEventDTO);

    Boolean deleteBudgetEventById(Long id);

    void finalizeBudgetEvent(Long moduleId, TaskStatus status);

    boolean softBudgetEventServiceById(Long id);
}
