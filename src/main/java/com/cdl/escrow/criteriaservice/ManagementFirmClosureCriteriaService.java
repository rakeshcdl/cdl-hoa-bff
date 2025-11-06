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
        Specification<ManagementFirmClosure> specification = createSpecification(criteria);
        return managementFirmClosureRepository.findAll(specification, pageable).map(managementFirmClosureMapper::toDto);
    }

    private Specification<ManagementFirmClosure> createSpecification(ManagementFirmClosureCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addDoubleFilter(cb, root, predicates, "reacTotalIncomeFund", criteria.getReacTotalIncomeFund());
                addDoubleFilter(cb, root, predicates, "reacTotalPayment", criteria.getReacTotalPayment());
                addDoubleFilter(cb, root, predicates, "reacCheckGuranteeDoc", criteria.getReacCheckGuranteeDoc());
                //addLongFilter(cb, root, predicates, "realEstateAssestId", criteria.getRealEstateAssestId());
               // addLongFilter(cb, root, predicates, "reacDocumentId", criteria.getReacDocumentId());


                if (criteria.getRealEstateAssestId() != null) {
                    Join<ManagementFirmClosure, ManagementFirm> join = root.join("realEstateAssest", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getRealEstateAssestId());
                }

               /* if (criteria.getRealEstateAssestId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "realEstateAssest", "id", criteria.getRealEstateAssestId());
                }*/
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
