package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.OwnerRegistryUnitTypeCriteria;
import com.cdl.escrow.dto.OwnerRegistryUnitTypeDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitType;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryUnitTypeMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitTypeRepository;
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
public class OwnerRegistryUnitTypeCriteriaService extends BaseSpecificationBuilder<OwnerRegistryUnitType>  implements Serializable {

    private final transient OwnerRegistryUnitTypeRepository ownerRegistryUnitTypeRepository;

    private final transient OwnerRegistryUnitTypeMapper ownerRegistryUnitTypeMapper;

    public Page<OwnerRegistryUnitTypeDTO> findByCriteria(OwnerRegistryUnitTypeCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryUnitType> specification = createSpecification(criteria);
        return ownerRegistryUnitTypeRepository.findAll(specification, pageable).map(ownerRegistryUnitTypeMapper::toDto);
    }


    public Specification<OwnerRegistryUnitType> createSpecification(OwnerRegistryUnitTypeCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                // Long Filters
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addLongFilter(cb, root, predicates, "capitalPartnerParentUnitTypeId", criteria.getCapitalPartnerParentUnitTypeId());

                // String Filters
                addStringFilter(cb, root, predicates, "ownutName", criteria.getOwnutName(), true);
                addStringFilter(cb, root, predicates, "ownutIconContentType", criteria.getOwnutIconContentType(), true);
                addStringFilter(cb, root, predicates, "ownUnitTypePrefix", criteria.getOwnUnitTypePrefix(), true);
                addStringFilter(cb, root, predicates, "ownutExcelFormula", criteria.getOwnutExcelFormula(), true);
                addStringFilter(cb, root, predicates, "ownutJsFormula", criteria.getOwnutJsFormula(), true);

                // Boolean Filters
                addBooleanFilter(cb, root, predicates, "ownutIsStandalone", criteria.getOwnutIsStandalone());
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
