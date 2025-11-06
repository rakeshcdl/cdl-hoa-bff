package com.cdl.escrow.repository;

import com.cdl.escrow.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Long>, JpaSpecificationExecutor<Budget> {

    Optional<Budget> findByIdAndDeletedFalse(Long id);
}

