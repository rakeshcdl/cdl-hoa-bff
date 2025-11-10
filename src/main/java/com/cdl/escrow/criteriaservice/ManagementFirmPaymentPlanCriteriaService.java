package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.ManagementFirmPaymentPlanCriteria;
import com.cdl.escrow.dto.ManagementFirmPaymentPlanDTO;
import com.cdl.escrow.entity.ManagementFirmPaymentPlan;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmPaymentPlanMapper;
import com.cdl.escrow.repository.ManagementFirmPaymentPlanRepository;
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
public class ManagementFirmPaymentPlanCriteriaService extends BaseSpecificationBuilder<ManagementFirmPaymentPlan> implements Serializable {

    private final transient ManagementFirmPaymentPlanRepository managementFirmPaymentPlanRepository;

    private final transient ManagementFirmPaymentPlanMapper managementFirmPaymentPlanMapper;

    public Page<ManagementFirmPaymentPlanDTO> findByCriteria(ManagementFirmPaymentPlanCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmPaymentPlan> specification = createSpecification(criteria);
        return managementFirmPaymentPlanRepository.findAll(specification, pageable).map(managementFirmPaymentPlanMapper::toDto);
    }

    private Specification<ManagementFirmPaymentPlan> createSpecification(ManagementFirmPaymentPlanCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());


                // Relation Join
                if (criteria.getManagementFirmId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "managementFirmId", "id", criteria.getManagementFirmId());
                }


            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
