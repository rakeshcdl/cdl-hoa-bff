package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmFeeHistoryCriteria;
import com.cdl.escrow.dto.ManagementFirmHistoryDTO;
import com.cdl.escrow.entity.ManagementFirmHistory;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmFeeHistoryMapper;
import com.cdl.escrow.repository.ManagementFirmFeeHistoryRepository;
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
public class ManagementFirmFeeHistoryCriteriaService extends BaseSpecificationBuilder<ManagementFirmHistory> implements Serializable {

    private final transient ManagementFirmFeeHistoryRepository managementFirmFeeHistoryRepository;

    private final transient ManagementFirmFeeHistoryMapper managementFirmFeeHistoryMapper;

    public Page<ManagementFirmHistoryDTO> findByCriteria(ManagementFirmFeeHistoryCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmHistory> specification = createSpecification(criteria);
        return managementFirmFeeHistoryRepository.findAll(specification, pageable).map(managementFirmFeeHistoryMapper::toDto);
    }

    private Specification<ManagementFirmHistory> createSpecification(ManagementFirmFeeHistoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "reafhAmount", criteria.getReafhAmount());
                addDoubleFilter(cb, root, predicates, "reafhTotalAmount", criteria.getReafhTotalAmount());
                addDoubleFilter(cb, root, predicates, "reafhVatPercentage", criteria.getReafhVatPercentage());
                addZonedDateTimeFilter(cb, root, predicates, "reafhTransactionDate", criteria.getReafhTransactionDate());
                addBooleanFilter(cb, root, predicates, "reafhSuccess", criteria.getReafhSuccess());
                addBooleanFilter(cb, root, predicates, "reafhStatus", criteria.getReafhStatus());
                addStringFilter(cb, root, predicates, "reahfRemark", criteria.getReahfRemark(), true);
                addStringFilter(cb, root, predicates, "reafhFeeResponse", criteria.getReafhFeeResponse(), true);
                addStringFilter(cb, root, predicates, "reafhResponseStatus", criteria.getReafhResponseStatus(), true);
                addStringFilter(cb, root, predicates, "reafhSpecialField1", criteria.getReafhSpecialField1(), true);
                addStringFilter(cb, root, predicates, "reafhSpecialField2", criteria.getReafhSpecialField2(), true);
                addStringFilter(cb, root, predicates, "reafhSpecialField3", criteria.getReafhSpecialField3(), true);
                addStringFilter(cb, root, predicates, "reafhSpecialField4", criteria.getReafhSpecialField4(), true);
                addStringFilter(cb, root, predicates, "reafhSpecialField5", criteria.getReafhSpecialField5(), true);
                addStringFilter(cb, root, predicates, "reafhFeeRequestBody", criteria.getReafhFeeRequestBody(), true);
                addLongFilter(cb, root, predicates, "realEstateAssestFeeId", criteria.getRealEstateAssestFeeId());
                addLongFilter(cb, root, predicates, "realEstateAssestId", criteria.getRealEstateAssestId());
                addLongFilter(cb, root, predicates, "capitalPartnerUnitId", criteria.getCapitalPartnerUnitId());
                addLongFilter(cb, root, predicates, "fundEgressId", criteria.getFundEgressId());
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
