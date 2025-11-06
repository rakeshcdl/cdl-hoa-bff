package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.AssetRegisterContactCriteria;
import com.cdl.escrow.dto.AssetRegisterContactDTO;
import com.cdl.escrow.entity.AssetRegisterContact;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AssetRegisterContactMapper;
import com.cdl.escrow.repository.AssetRegisterContactRepository;
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
public class AssetRegisterContactCriteriaService extends BaseSpecificationBuilder<AssetRegisterContact> implements Serializable {

    private final transient AssetRegisterContactRepository assetRegisterContactRepository;

    private final transient AssetRegisterContactMapper assetRegisterContactMapper;

    public Page<AssetRegisterContactDTO> findByCriteria(AssetRegisterContactCriteria criteria, Pageable pageable) {
        Specification<AssetRegisterContact> specification = createSpecification(criteria);
        return assetRegisterContactRepository.findAll(specification, pageable).map(assetRegisterContactMapper::toDto);
    }

    public Specification<AssetRegisterContact> createSpecification(AssetRegisterContactCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "bpcContactName", criteria.getArcContactName(), true);
                addStringFilter(cb, root, predicates, "bpcCcontactTelCode", criteria.getArcCcontactTelCode(), false);
                addStringFilter(cb, root, predicates, "bpcCcontactTelNo", criteria.getArcCcontactTelNo(), false);
                addStringFilter(cb, root, predicates, "bpcCcountryMobCode", criteria.getArcCcountryMobCode(), false);
                addStringFilter(cb, root, predicates, "bpcCcontactMobNo", criteria.getArcCcontactMobNo(), false);
                addStringFilter(cb, root, predicates, "bpcCcontactEmail", criteria.getArcCcontactEmail(), true);
                addStringFilter(cb, root, predicates, "bpcCcontactAddress", criteria.getArcCcontactAddress(), true);
                addStringFilter(cb, root, predicates, "bpcCcontactPoBox", criteria.getArcCcontactPoBox(), false);
                addStringFilter(cb, root, predicates, "bpcCcontactFaxNo", criteria.getArcCcontactFaxNo(), false);

                // Enum or direct equals
                if (criteria.getWorkflowStatus() != null) {
                    predicates.add(cb.equal(root.get("workflowStatus"), criteria.getWorkflowStatus()));
                }

                // Relation Join
                if (criteria.getAssetRegisterId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "buildPartner", "id", criteria.getAssetRegisterId());
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
