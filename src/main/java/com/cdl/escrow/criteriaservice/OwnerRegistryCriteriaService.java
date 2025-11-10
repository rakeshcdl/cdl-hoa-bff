package com.cdl.escrow.criteriaservice;



import com.cdl.escrow.criteria.OwnerRegistryCriteria;
import com.cdl.escrow.dto.OwnerRegistryDTO;
import com.cdl.escrow.entity.OwnerRegistry;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryMapper;
import com.cdl.escrow.repository.OwnerRegistryRepository;
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
public class OwnerRegistryCriteriaService extends BaseSpecificationBuilder<OwnerRegistry> implements Serializable {

    private final transient OwnerRegistryRepository ownerRegistryRepository;

    private final transient OwnerRegistryMapper ownerRegistryMapper;

    public Page<OwnerRegistryDTO> findByCriteria(OwnerRegistryCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistry> specification = createSpecification(criteria);
        return ownerRegistryRepository.findAll(specification, pageable).map(ownerRegistryMapper::toDto);
    }

    public Specification<OwnerRegistry> createSpecification(OwnerRegistryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                // Long Filters
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addLongFilter(cb, root, predicates, "documentTypeId", criteria.getDocumentTypeId());
                addLongFilter(cb, root, predicates, "countryOptionId", criteria.getCountryOptionId());
                addLongFilter(cb, root, predicates, "investorTypeId", criteria.getInvestorTypeId());
                addLongFilter(cb, root, predicates, "ownerRegistryUnitId", criteria.getOwnerRegistryUnitId());

                // String Filters
                addStringFilter(cb, root, predicates, "ownerRegistryId", criteria.getOwnerRegistryId(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryName", criteria.getOwnerRegistryName(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryMiddleName", criteria.getOwnerRegistryMiddleName(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryLastName", criteria.getOwnerRegistryLastName(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryIdNo", criteria.getOwnerRegistryIdNo(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryTelephoneNo", criteria.getOwnerRegistryTelephoneNo(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryMobileNo", criteria.getOwnerRegistryMobileNo(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryEmail", criteria.getOwnerRegistryEmail(), true);
                addStringFilter(cb, root, predicates, "ownerRegistryLocaleName", criteria.getOwnerRegistryLocaleName(), true);

                // Float Filter
                addFloatFilter(cb, root, predicates, "ownerRegistryOwnershipPercentage", criteria.getOwnerRegistryOwnershipPercentage());

                // Integer Filter
                addIntegerFilter(cb, root, predicates, "ownerRegistryOwnerNumber", criteria.getOwnerRegistryOwnerNumber());

                // Boolean Filter
                addBooleanFilter(cb, root, predicates, "isCurrent", criteria.getIsCurrent());

                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());

                // ZonedDateTime Filter
                addZonedDateTimeFilter(cb, root, predicates, "idExpiaryDate", criteria.getIdExpiaryDate());
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
