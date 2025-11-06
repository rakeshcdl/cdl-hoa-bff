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
        Specification<ManagementFirmFee> specification = createSpecification(criteria);
        return managementFirmFeeRepository.findAll(specification, pageable).map(managementFirmFeeMapper::toDto);
    }

    private Specification<ManagementFirmFee> createSpecification(ManagementFirmFeeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "reafAmount", criteria.getReafAmount());
                addDoubleFilter(cb, root, predicates, "reafTotalAmount", criteria.getReafTotalAmount());
                addZonedDateTimeFilter(cb, root, predicates, "reafCalender", criteria.getReafCalender());
                addZonedDateTimeFilter(cb, root, predicates, "reafNextRecoveryDate", criteria.getReafNextRecoveryDate());
                addDoubleFilter(cb, root, predicates, "reafVatPercentage", criteria.getReafVatPercentage());
                addBooleanFilter(cb, root, predicates, "reafCollected", criteria.getReafCollected());


                // Relation Join
                if (criteria.getRealEstateAssestId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "realEstateAssest", "id", criteria.getRealEstateAssestId());
                }
                if (criteria.getReafCategoryId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "reafCategory", "id", criteria.getReafCategoryId());
                }
                if (criteria.getReafCurrencyId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "reafCurrency", "id", criteria.getReafCurrencyId());
                }
                if (criteria.getReafFrequencyId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "reafFrequency", "id", criteria.getReafFrequencyId());
                }


            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
