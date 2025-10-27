package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BudgetItemCriteria;
import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BudgetItemMapper;
import com.cdl.escrow.repository.BudgetItemRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetItemCriteriaService  extends BaseSpecificationBuilder<BudgetItem> implements Serializable {
    private final transient BudgetItemRepository budgetItemRepository;

    private final transient BudgetItemMapper budgetItemMapper;

    public Page<BudgetItemDTO> findByCriteria(BudgetItemCriteria criteria, Pageable pageable) {
        Specification<BudgetItem> specification = createSpecification(criteria);
        return budgetItemRepository.findAll(specification, pageable).map(budgetItemMapper::toDto);
    }

    public Specification<BudgetItem> createSpecification(BudgetItemCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "subCategoryCode", criteria.getSubCategoryCode(), true);
                addStringFilter(cb, root, predicates, "subCategoryName", criteria.getSubCategoryName(),true);
                addStringFilter(cb, root, predicates, "subCategoryNameLocale", criteria.getSubCategoryNameLocale(), true);
                addStringFilter(cb, root, predicates, "serviceCode", criteria.getServiceCode(),true);
                addStringFilter(cb, root, predicates, "provisionalServiceCode", criteria.getProvisionalServiceCode(), true);
                addStringFilter(cb, root, predicates, "serviceName", criteria.getServiceName(), true);
                addStringFilter(cb, root, predicates, "serviceNameLocale", criteria.getServiceNameLocale(),true);
                addDoubleFilter(cb, root, predicates, "totalBudget", criteria.getTotalBudget());
                addDoubleFilter(cb, root, predicates, "availableBudget", criteria.getAvailableBudget());
                addDoubleFilter(cb, root, predicates, "utilizedBudget", criteria.getUtilizedBudget());


                // relationships

                if (criteria.getBudgetCategoryId() != null) {
                    Join<BudgetItem, BudgetCategory> join = root.join("budgetCategory", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBudgetCategoryId());
                }



            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}