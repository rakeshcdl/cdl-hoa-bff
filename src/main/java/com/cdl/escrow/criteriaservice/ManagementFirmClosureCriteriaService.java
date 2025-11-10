package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmClosureCriteria;
import com.cdl.escrow.dto.ManagementFirmClosureDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.entity.ManagementFirmClosure;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmClosureMapper;
import com.cdl.escrow.repository.ManagementFirmClosureRepository;
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
public class ManagementFirmClosureCriteriaService extends BaseSpecificationBuilder<ManagementFirmClosure> implements Serializable {

    private final transient ManagementFirmClosureRepository managementFirmClosureRepository;

    private final transient ManagementFirmClosureMapper managementFirmClosureMapper;

    public Page<ManagementFirmClosureDTO> findByCriteria(ManagementFirmClosureCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmClosure> specification = cmfteSpecification(criteria);
        return managementFirmClosureRepository.findAll(specification, pageable).map(managementFirmClosureMapper::toDto);
    }

    private Specification<ManagementFirmClosure> cmfteSpecification(ManagementFirmClosureCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "mfcTotalIncomeFund", criteria.getMfcTotalIncomeFund());
                addDoubleFilter(cb, root, predicates, "mfcTotalPayment", criteria.getMfcTotalPayment());
                addDoubleFilter(cb, root, predicates, "mfcCheckGuaranteeDoc", criteria.getMfcCheckGuaranteeDoc());
                //addLongFilter(cb, root, predicates, "mflEstateAssestId", criteria.getMflEstateAssestId());
               // addLongFilter(cb, root, predicates, "mfcDocumentId", criteria.getMfcDocumentId());


                if (criteria.getManagementFirmId() != null) {
                    Join<ManagementFirmClosure, ManagementFirm> join = root.join("managementFirmId", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getManagementFirmId());
                }

               /* if (criteria.getMflEstateAssestId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "mflEstateAssest", "id", criteria.getMflEstateAssestId());
                }*/
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
