package com.cdl.escrow.repository;


import com.cdl.escrow.entity.BudgetItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BudgetItemRepository extends JpaRepository<BudgetItem,Long>, JpaSpecificationExecutor<BudgetItem> {
    Optional<BudgetItem> findByIdAndDeletedFalse(Long id);
}
