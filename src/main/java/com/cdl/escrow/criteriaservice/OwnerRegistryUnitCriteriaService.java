package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.OwnerRegistryUnitCriteria;
import com.cdl.escrow.dto.OwnerRegistryUnitDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.OwnerRegistry;
import com.cdl.escrow.entity.OwnerRegistryUnit;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryUnitMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitRepository;
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
public class OwnerRegistryUnitCriteriaService extends BaseSpecificationBuilder<OwnerRegistryUnit> implements Serializable {

    private final transient OwnerRegistryUnitRepository ownerRegistryUnitRepository;

    private final transient OwnerRegistryUnitMapper ownerRegistryUnitMapper;

    public Page<OwnerRegistryUnitDTO> findByCriteria(OwnerRegistryUnitCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryUnit> specification = createSpecification(criteria);
        return ownerRegistryUnitRepository.findAll(specification, pageable).map(ownerRegistryUnitMapper::toDto);
    }


    public Specification<OwnerRegistryUnit> createSpecification(OwnerRegistryUnitCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                // Long filters
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addLongFilter(cb, root, predicates, "partnerUnitId", criteria.getPartnerUnitId());
                addLongFilter(cb, root, predicates, "capitalPartnerUnitTypeId", criteria.getCapitalPartnerUnitTypeId());
                addLongFilter(cb, root, predicates, "realEstateAssestId", criteria.getRealEstateAssestId());
                addLongFilter(cb, root, predicates, "unitStatusId", criteria.getUnitStatusId());
                addLongFilter(cb, root, predicates, "propertyId", criteria.getPropertyId());
                addLongFilter(cb, root, predicates, "paymentPlanTypeId", criteria.getPaymentPlanTypeId());
                addLongFilter(cb, root, predicates, "capitalPartnerUnitBookingId", criteria.getCapitalPartnerUnitBookingId());

                // String filters
                addStringFilter(cb, root, predicates, "unitRefId", criteria.getUnitRefId(), true);
                addStringFilter(cb, root, predicates, "altUnitRefId", criteria.getAltUnitRefId(), true);
                addStringFilter(cb, root, predicates, "name", criteria.getName(), true);
                addStringFilter(cb, root, predicates, "unitSysId", criteria.getUnitSysId(), true);
                addStringFilter(cb, root, predicates, "otherFormatUnitNo", criteria.getOtherFormatUnitNo(), true);
                addStringFilter(cb, root, predicates, "virtualAccNo", criteria.getVirtualAccNo(), true);
                addStringFilter(cb, root, predicates, "towerName", criteria.getTowerName(), true);
                addStringFilter(cb, root, predicates, "unitPlotSize", criteria.getUnitPlotSize(), true);
                addStringFilter(cb, root, predicates, "floor", criteria.getFloor(), true);
                addStringFilter(cb, root, predicates, "noofBedroom", criteria.getNoofBedroom(), true);

                // Boolean filters
                addBooleanFilter(cb, root, predicates, "isResale", criteria.getIsResale());
                addBooleanFilter(cb, root, predicates, "isModified", criteria.getIsModified());

                // ZonedDateTime filters
                addZonedDateTimeFilter(cb, root, predicates, "resaleDate", criteria.getResaleDate());

                // Filter by OwnerRegistry id -> join the ownerRegistries collection
                if (criteria.getCapitalPartnerId() != null) {
                    Join<OwnerRegistryUnit, OwnerRegistry> join = root.join("ownerRegistries", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCapitalPartnerId());
                }

                if (criteria.getCapitalPartnerUnitTypeId() != null) {
                    Join<OwnerRegistryUnit, ApplicationSetting> join = root.join("ownerRegistryUnitType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCapitalPartnerUnitTypeId());
                }

                if (criteria.getUnitStatusId() != null) {
                    Join<OwnerRegistryUnit, ApplicationSetting> join = root.join("unitStatus", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getUnitStatusId());
                }

                if (criteria.getPropertyId() != null) {
                    Join<OwnerRegistryUnit, ApplicationSetting> join = root.join("propertyId", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPropertyId());
                }

                if (criteria.getPaymentPlanTypeId() != null) {
                    Join<OwnerRegistryUnit, ApplicationSetting> join = root.join("paymentPlanType", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentPlanTypeId());
                }

                if (criteria.getCapitalPartnerUnitBookingId() != null) {
                    Join<OwnerRegistryUnit, ApplicationSetting> join = root.join("ownerRegistryUnitBooking", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getCapitalPartnerUnitBookingId());
                }

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
