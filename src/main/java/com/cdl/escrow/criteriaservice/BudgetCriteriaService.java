package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BudgetCriteria;
import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.entity.AssetRegister;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BudgetMapper;
import com.cdl.escrow.repository.BudgetRepository;
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
public class BudgetCriteriaService extends BaseSpecificationBuilder<Budget> implements Serializable {
    private final transient BudgetRepository budgetRepository;

    private final transient BudgetMapper budgetMapper;

    public Page<BudgetDTO> findByCriteria(BudgetCriteria criteria, Pageable pageable) {
        Specification<Budget> specification = createSpecification(criteria);
        return budgetRepository.findAll(specification, pageable).map(budgetMapper::toDto);
    }

    public Specification<Budget> createSpecification(BudgetCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "budgetId", criteria.getBudgetId(), true);
                addBooleanFilter(cb, root, predicates, "isActive", criteria.getIsActive());
                addStringFilter(cb, root, predicates, "budgetPeriodCode", criteria.getBudgetPeriodCode(), true);
                addIntegerFilter(cb, root, predicates, "propertyGroupId", criteria.getPropertyGroupId());
                addStringFilter(cb, root, predicates, "propertyManagerEmail", criteria.getPropertyManagerEmail(), true);
                addStringFilter(cb, root, predicates, "masterCommunityName", criteria.getMasterCommunityName(), true);
                addStringFilter(cb, root, predicates, "masterCommunityNameLocale", criteria.getMasterCommunityNameLocale(),true);
                addStringFilter(cb, root, predicates, "createdBy", criteria.getCreatedBy(), true);

                // relationships

                if (criteria.getBuildPartnerId() != null) {
                    Join<Budget, AssetRegister> join = root.join("buildPartner", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBuildPartnerId());
                }

                if (criteria.getRealEstateAssestId() != null) {
                    Join<Budget, ManagementFirm> join = root.join("realEstateAssest", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getRealEstateAssestId());
                }

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
