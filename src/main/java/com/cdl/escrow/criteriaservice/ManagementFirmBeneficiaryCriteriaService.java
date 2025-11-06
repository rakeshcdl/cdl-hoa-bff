package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmBeneficiaryCriteria;
import com.cdl.escrow.dto.ManagementFirmBeneficiaryDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.entity.ManagementFirmBeneficiary;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmBeneficiaryMapper;
import com.cdl.escrow.repository.ManagementFirmBeneficiaryRepository;
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
public class ManagementFirmBeneficiaryCriteriaService extends BaseSpecificationBuilder<ManagementFirmBeneficiary>  implements Serializable {

    private final transient ManagementFirmBeneficiaryRepository managementFirmBeneficiaryRepository;

    private final transient ManagementFirmBeneficiaryMapper managementFirmBeneficiaryMapper;

    public Page<ManagementFirmBeneficiaryDTO> findByCriteria(ManagementFirmBeneficiaryCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmBeneficiary> specification = createSpecification(criteria);
        return managementFirmBeneficiaryRepository.findAll(specification, pageable).map(managementFirmBeneficiaryMapper::toDto);
    }

    private Specification<ManagementFirmBeneficiary> createSpecification(ManagementFirmBeneficiaryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "reabBeneficiaryId", criteria.getReabBeneficiaryId(), true);
                addStringFilter(cb, root, predicates, "reabName", criteria.getReabName(), true);
                addDoubleFilter(cb, root, predicates, "reabContractAmount", criteria.getReabContractAmount());
                addDoubleFilter(cb, root, predicates, "reabActualLandPrice", criteria.getReabActualLandPrice());
                addStringFilter(cb, root, predicates, "reabContractorName", criteria.getReabContractorName(), true);
                addStringFilter(cb, root, predicates, "reabType", criteria.getReabType(), true);
                addStringFilter(cb, root, predicates, "reabBank", criteria.getReabBank(), true);
                addStringFilter(cb, root, predicates, "reabSwift", criteria.getReabSwift(), true);
                addStringFilter(cb, root, predicates, "reabRoutingCode", criteria.getReabRoutingCode(), true);
                addStringFilter(cb, root, predicates, "reabAddress", criteria.getReabAddress(), true);
                addStringFilter(cb, root, predicates, "reabBankAddress", criteria.getReabBankAddress(), true);
                addBooleanFilter(cb, root, predicates, "reabIsActive", criteria.getReabIsActive());
                addBooleanFilter(cb, root, predicates, "reabIsDeleted", criteria.getReabIsDeleted());
                addLongFilter(cb, root, predicates, "reabTranferTypeId", criteria.getReabTranferTypeId());
                addLongFilter(cb, root, predicates, "reabExpenseTypeId", criteria.getReabExpenseTypeId());
                addLongFilter(cb, root, predicates, "reabVendorSubTypeId", criteria.getReabVendorSubTypeId());
                addLongFilter(cb, root, predicates, "reabContractorSubTypeId", criteria.getReabContractorSubTypeId());
                addLongFilter(cb, root, predicates, "reabInfrastructureCategoryId", criteria.getReabInfrastructureCategoryId());
                addLongFilter(cb, root, predicates, "reabSalesCategoryId", criteria.getReabSalesCategoryId());


                // Filter by CapitalPartner id -> join the capitalPartners collection
                if (criteria.getRealEstateAssestId() != null) {
                    Join<ManagementFirmBeneficiary, ManagementFirm> join = root.join("realEstateAssests", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getRealEstateAssestId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
