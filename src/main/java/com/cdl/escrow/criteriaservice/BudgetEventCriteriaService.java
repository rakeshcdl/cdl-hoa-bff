package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.BudgetEventCriteria;
import com.cdl.escrow.dto.BudgetEventDTO;
import com.cdl.escrow.entity.BudgetEvent;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.BudgetEventMapper;
import com.cdl.escrow.repository.BudgetEventRepository;
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
public class BudgetEventCriteriaService extends BaseSpecificationBuilder<BudgetEvent> implements Serializable {
    private final transient BudgetEventRepository budgetEventRepository;

    private final transient BudgetEventMapper budgetEventMapper;

    public Page<BudgetEventDTO> findByCriteria(BudgetEventCriteria criteria, Pageable pageable) {
        Specification<BudgetEvent> specification = createSpecification(criteria);
        return budgetEventRepository.findAll(specification, pageable).map(budgetEventMapper::toDto);
    }

    public Specification<BudgetEvent> createSpecification(BudgetEventCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "eventId", criteria.getEventId(), true);
                addStringFilter(cb, root, predicates, "urc", criteria.getUrc(),true);
                addZonedDateTimeFilter(cb, root, predicates, "timeStamp", criteria.getTimeStamp());
                addStringFilter(cb, root, predicates, "syncType", criteria.getSyncType(),true);
                addStringFilter(cb, root, predicates, "propertyGroupId", criteria.getPropertyGroupId(), true);
                addStringFilter(cb, root, predicates, "periodCode", criteria.getPeriodCode(), true);
                addStringFilter(cb, root, predicates, "managementCompanyId", criteria.getManagementCompanyId(),true);
                addLongFilter(cb, root, predicates, "acknowledgeRef", criteria.getAcknowledgeRef());


            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}