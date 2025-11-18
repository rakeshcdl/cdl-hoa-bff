package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.BudgetCategoryCriteria;
import com.cdl.escrow.dto.BudgetCategoryDTO;
import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.entity.BudgetCategory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BudgetCategoryMapper;
import com.cdl.escrow.repository.BudgetCategoryRepository;
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
public class BudgetCategoryCriteriaService extends BaseSpecificationBuilder<BudgetCategory> implements Serializable {
    private final transient BudgetCategoryRepository budgetCategoryRepository;

    private final transient BudgetCategoryMapper budgetCategoryMapper;

    public Page<BudgetCategoryDTO> findByCriteria(BudgetCategoryCriteria criteria, Pageable pageable) {
        Specification<BudgetCategory> specification = createSpecification(criteria);
        return budgetCategoryRepository.findAll(specification, pageable).map(budgetCategoryMapper::toDto);
    }

    public Specification<BudgetCategory> createSpecification(BudgetCategoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addIntegerFilter(cb, root, predicates, "serviceChargeGroupId", criteria.getServiceChargeGroupId());
                addStringFilter(cb, root, predicates, "serviceChargeGroupName", criteria.getServiceChargeGroupName(), true);
                addStringFilter(cb, root, predicates, "serviceChargeGroupNameLocale", criteria.getServiceChargeGroupNameLocale(),true);
                addStringFilter(cb, root, predicates, "usageLocale", criteria.getUsageLocale(), true);
                addStringFilter(cb, root, predicates, "usage", criteria.getUsage(), true);
                addZonedDateTimeFilter(cb, root, predicates, "budgetPeriodFrom", criteria.getBudgetPeriodFrom());
                addZonedDateTimeFilter(cb, root, predicates, "budgetPeriodTo", criteria.getBudgetPeriodTo());
                addStringFilter(cb, root, predicates, "budgetPeriodTitle", criteria.getBudgetPeriodTitle(), true);
                addStringFilter(cb, root, predicates, "categoryCode", criteria.getCategoryCode(), true);
                addStringFilter(cb, root, predicates, "categoryName", criteria.getCategoryName(), true);
                addStringFilter(cb, root, predicates, "categoryNameLocale", criteria.getCategoryNameLocale(), true);
                addDoubleFilter(cb, root, predicates, "vatAmount", criteria.getVatAmount());

                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                // relationships

                if (criteria.getBudgetId() != null) {
                    Join<BudgetCategory, Budget> join = root.join("budget", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBudgetId());
                }

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
