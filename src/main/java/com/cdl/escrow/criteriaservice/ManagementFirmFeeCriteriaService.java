package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmFeeCriteria;
import com.cdl.escrow.dto.ManagementFirmFeeDTO;
import com.cdl.escrow.entity.ManagementFirmFee;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmFeeMapper;
import com.cdl.escrow.repository.ManagementFirmFeeRepository;
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
public class ManagementFirmFeeCriteriaService extends BaseSpecificationBuilder<ManagementFirmFee>  implements Serializable {

    private final transient ManagementFirmFeeRepository managementFirmFeeRepository;

    private final transient ManagementFirmFeeMapper managementFirmFeeMapper;

    public Page<ManagementFirmFeeDTO> findByCriteria(ManagementFirmFeeCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmFee> specification = cmfteSpecification(criteria);
        return managementFirmFeeRepository.findAll(specification, pageable).map(managementFirmFeeMapper::toDto);
    }

    private Specification<ManagementFirmFee> cmfteSpecification(ManagementFirmFeeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "mffAmount", criteria.getMffAmount());
                addDoubleFilter(cb, root, predicates, "mffTotalAmount", criteria.getMffTotalAmount());
                addZonedDateTimeFilter(cb, root, predicates, "mffCalender", criteria.getMffCalender());
                addZonedDateTimeFilter(cb, root, predicates, "mffNextRecoveryDate", criteria.getMffNextRecoveryDate());
                addDoubleFilter(cb, root, predicates, "mffVatPercentage", criteria.getMffVatPercentage());
                addBooleanFilter(cb, root, predicates, "mffCollected", criteria.getMffCollected());

                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                // Relation Join
                if (criteria.getManagementFirmId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "managementFirm", "id", criteria.getManagementFirmId());
                }
                if (criteria.getMffCategoryId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "mffCategory", "id", criteria.getMffCategoryId());
                }
                if (criteria.getMffCurrencyId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "mffCurrency", "id", criteria.getMffCurrencyId());
                }
                if (criteria.getMffFrequencyId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "mffFrequency", "id", criteria.getMffFrequencyId());
                }


            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
