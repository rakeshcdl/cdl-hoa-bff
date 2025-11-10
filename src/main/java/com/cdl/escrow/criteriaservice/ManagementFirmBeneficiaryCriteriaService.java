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
        Specification<ManagementFirmBeneficiary> specification = cmfteSpecification(criteria);
        return managementFirmBeneficiaryRepository.findAll(specification, pageable).map(managementFirmBeneficiaryMapper::toDto);
    }

    private Specification<ManagementFirmBeneficiary> cmfteSpecification(ManagementFirmBeneficiaryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "mfbBeneficiaryId", criteria.getMfbBeneficiaryId(), true);
                addStringFilter(cb, root, predicates, "mfbName", criteria.getMfbName(), true);
                addDoubleFilter(cb, root, predicates, "mfbContractAmount", criteria.getMfbContractAmount());
                addDoubleFilter(cb, root, predicates, "mfbActualLandPrice", criteria.getMfbActualLandPrice());
                addStringFilter(cb, root, predicates, "mfbContractorName", criteria.getMfbContractorName(), true);
                addStringFilter(cb, root, predicates, "mfbType", criteria.getMfbType(), true);
                addStringFilter(cb, root, predicates, "mfbBank", criteria.getMfbBank(), true);
                addStringFilter(cb, root, predicates, "mfbSwift", criteria.getMfbSwift(), true);
                addStringFilter(cb, root, predicates, "mfbRoutingCode", criteria.getMfbRoutingCode(), true);
                addStringFilter(cb, root, predicates, "mfbAddress", criteria.getMfbAddress(), true);
                addStringFilter(cb, root, predicates, "mfbBankAddress", criteria.getMfbBankAddress(), true);
                addBooleanFilter(cb, root, predicates, "mfbIsActive", criteria.getMfbIsActive());
                addBooleanFilter(cb, root, predicates, "mfbIsDeleted", criteria.getMfbIsDeleted());
                addLongFilter(cb, root, predicates, "mfbTransferTypeId", criteria.getMfbTransferTypeId());
                addLongFilter(cb, root, predicates, "mfbExpenseTypeId", criteria.getMfbExpenseTypeId());
                addLongFilter(cb, root, predicates, "mfbVendorSubTypeId", criteria.getMfbVendorSubTypeId());
                addLongFilter(cb, root, predicates, "mfbContractorSubTypeId", criteria.getMfbContractorSubTypeId());
                addLongFilter(cb, root, predicates, "mfbInfrastructureCategoryId", criteria.getMfbInfrastructureCategoryId());
                addLongFilter(cb, root, predicates, "mfbSalesCategoryId", criteria.getMfbSalesCategoryId());


                // Filter by OwnerRegistry id -> join the ownerRegistries collection
                if (criteria.getManagementFirmsId() != null) {
                    Join<ManagementFirmBeneficiary, ManagementFirm> join = root.join("managementFirmsId", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getManagementFirmsId());
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
