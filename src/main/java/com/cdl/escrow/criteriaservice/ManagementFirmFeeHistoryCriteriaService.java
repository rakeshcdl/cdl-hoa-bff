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
        Specification<ManagementFirmHistory> specification = cmfteSpecification(criteria);
        return managementFirmFeeHistoryRepository.findAll(specification, pageable).map(managementFirmFeeHistoryMapper::toDto);
    }

    private Specification<ManagementFirmHistory> cmfteSpecification(ManagementFirmFeeHistoryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "mffhAmount", criteria.getMffhAmount());
                addDoubleFilter(cb, root, predicates, "mffhTotalAmount", criteria.getMffhTotalAmount());
                addDoubleFilter(cb, root, predicates, "mffhVatPercentage", criteria.getMffhVatPercentage());
                addZonedDateTimeFilter(cb, root, predicates, "mffhTransactionDate", criteria.getMffhTransactionDate());
                addBooleanFilter(cb, root, predicates, "mffhSuccess", criteria.getMffhSuccess());
                addBooleanFilter(cb, root, predicates, "mffhStatus", criteria.getMffhStatus());
                addStringFilter(cb, root, predicates, "mfhfRemark", criteria.getMfhfRemark(), true);
                addStringFilter(cb, root, predicates, "mffhFeeResponse", criteria.getMffhFeeResponse(), true);
                addStringFilter(cb, root, predicates, "mffhResponseStatus", criteria.getMffhResponseStatus(), true);
                addStringFilter(cb, root, predicates, "mffhSpecialField1", criteria.getMffhSpecialField1(), true);
                addStringFilter(cb, root, predicates, "mffhSpecialField2", criteria.getMffhSpecialField2(), true);
                addStringFilter(cb, root, predicates, "mffhSpecialField3", criteria.getMffhSpecialField3(), true);
                addStringFilter(cb, root, predicates, "mffhSpecialField4", criteria.getMffhSpecialField4(), true);
                addStringFilter(cb, root, predicates, "mffhSpecialField5", criteria.getMffhSpecialField5(), true);
                addStringFilter(cb, root, predicates, "mffhFeeRequestBody", criteria.getMffhFeeRequestBody(), true);
                addLongFilter(cb, root, predicates, "managementFirmFeeId", criteria.getManagementFirmFeeId());
                addLongFilter(cb, root, predicates, "managementFirmId", criteria.getManagementFirmId());
                addLongFilter(cb, root, predicates, "ownerRegistryUnitId", criteria.getOwnerRegistryUnitId());

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
