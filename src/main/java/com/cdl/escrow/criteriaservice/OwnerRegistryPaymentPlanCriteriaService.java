package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.OwnerRegistryPaymentPlanCriteria;
import com.cdl.escrow.dto.OwnerRegistryPaymentPlanDTO;
import com.cdl.escrow.entity.OwnerRegistryPaymentPlan;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryPaymentPlanMapper;
import com.cdl.escrow.repository.OwnerRegistryPaymentPlanRepository;
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
public class OwnerRegistryPaymentPlanCriteriaService extends BaseSpecificationBuilder<OwnerRegistryPaymentPlan> implements Serializable {

    private final OwnerRegistryPaymentPlanRepository ownerRegistryPaymentPlanRepository;

    private final OwnerRegistryPaymentPlanMapper ownerRegistryPaymentPlanMapper;

    public Page<OwnerRegistryPaymentPlanDTO> findByCriteria(OwnerRegistryPaymentPlanCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryPaymentPlan> specification = createSpecification(criteria);
        return ownerRegistryPaymentPlanRepository.findAll(specification, pageable).map(ownerRegistryPaymentPlanMapper::toDto);
    }

    private Specification<OwnerRegistryPaymentPlan> createSpecification(OwnerRegistryPaymentPlanCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria != null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());

                if (criteria.getCapitalPartnerId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "ownerRegistry", "id", criteria.getCapitalPartnerId());
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
