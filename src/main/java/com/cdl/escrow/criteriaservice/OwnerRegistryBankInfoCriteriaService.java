package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.OwnerRegistryBankInfoCriteria;
import com.cdl.escrow.dto.OwnerRegistryBankInfoDTO;
import com.cdl.escrow.entity.OwnerRegistryBankInfo;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryBankInfoMapper;
import com.cdl.escrow.repository.OwnerRegistryBankInfoRepository;
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
public class OwnerRegistryBankInfoCriteriaService extends BaseSpecificationBuilder<OwnerRegistryBankInfo> implements Serializable {

    private final transient OwnerRegistryBankInfoRepository ownerRegistryBankInfoRepository;

    private final transient OwnerRegistryBankInfoMapper ownerRegistryBankInfoMapper;

    public Page<OwnerRegistryBankInfoDTO> findByCriteria(OwnerRegistryBankInfoCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryBankInfo> specification = createSpecification(criteria);
        return ownerRegistryBankInfoRepository.findAll(specification, pageable).map(ownerRegistryBankInfoMapper::toDto);
    }

    public Specification<OwnerRegistryBankInfo> createSpecification(OwnerRegistryBankInfoCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "cpbiPayeeName", criteria.getCpbiPayeeName(), true);
                addStringFilter(cb, root, predicates, "cpbiPayeeAddress", criteria.getCpbiPayeeAddress(), true);
                addStringFilter(cb, root, predicates, "cpbiBankName", criteria.getCpbiBankName(), true);
                addStringFilter(cb, root, predicates, "cpbiBankAddress", criteria.getCpbiBankAddress(), true);
                addStringFilter(cb, root, predicates, "cpbiBicCode", criteria.getCpbiBicCode(), true);
                addStringFilter(cb, root, predicates, "cpbiBeneRoutingCode", criteria.getCpbiBeneRoutingCode(), true);
               // addLongFilter(cb, root, predicates, "bankAccountId", criteria.getBankAccountId());
                //addLongFilter(cb, root, predicates, "capitalPartnerId", criteria.getCapitalPartnerId());
                //addLongFilter(cb, root, predicates, "payModeId", criteria.getPayModeId());

                // Relation Join
                if (criteria.getCapitalPartnerId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "ownerRegistry", "id", criteria.getCapitalPartnerId());
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
