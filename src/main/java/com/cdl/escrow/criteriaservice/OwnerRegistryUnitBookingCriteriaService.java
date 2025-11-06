package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.OwnerRegistryUnitBookingCriteria;
import com.cdl.escrow.dto.OwnerRegistryUnitBookingDTO;
import com.cdl.escrow.entity.OwnerRegistryUnit;
import com.cdl.escrow.entity.OwnerRegistryUnitBooking;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryUnitBookingMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitBookingRepository;
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
public class OwnerRegistryUnitBookingCriteriaService extends BaseSpecificationBuilder<OwnerRegistryUnitBooking> implements Serializable {

    private final transient OwnerRegistryUnitBookingRepository ownerRegistryUnitBookingRepository;

    private final transient OwnerRegistryUnitBookingMapper ownerRegistryUnitBookingMapper;

    public Page<OwnerRegistryUnitBookingDTO> findByCriteria(OwnerRegistryUnitBookingCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryUnitBooking> specification = createSpecification(criteria);
        return ownerRegistryUnitBookingRepository.findAll(specification, pageable).map(ownerRegistryUnitBookingMapper::toDto);
    }


    public Specification<OwnerRegistryUnitBooking> createSpecification(OwnerRegistryUnitBookingCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                // Long filters
                addLongFilter(cb, root, predicates, "id", criteria.getId());

                // Double filters
                addDoubleFilter(cb, root, predicates, "cpubAmountPaid", criteria.getCpubAmountPaid());
                addDoubleFilter(cb, root, predicates, "cpubAreaSize", criteria.getCpubAreaSize());
                addDoubleFilter(cb, root, predicates, "cpubForFeitAmount", criteria.getCpubForFeitAmount());
                addDoubleFilter(cb, root, predicates, "cpubDldAmount", criteria.getCpubDldAmount());
                addDoubleFilter(cb, root, predicates, "cpubRefundAmount", criteria.getCpubRefundAmount());
                addDoubleFilter(cb, root, predicates, "cpubTransferredAmount", criteria.getCpubTransferredAmount());

                // String filters
                addStringFilter(cb, root, predicates, "cpubRemarks", criteria.getCpubRemarks(), true);

                // Filter by OwnerRegistry id -> join the ownerRegistries collection
                if (criteria.getCapitalPartnerUnitId() != null) {
                    Join<OwnerRegistryUnitBooking, OwnerRegistryUnit> join = root.join("ownerRegistryUnits", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCapitalPartnerUnitId());
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
