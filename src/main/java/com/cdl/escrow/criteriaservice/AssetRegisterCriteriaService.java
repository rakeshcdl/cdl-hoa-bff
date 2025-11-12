package com.cdl.escrow.criteriaservice;



import com.cdl.escrow.criteria.AssetRegisterCriteria;
import com.cdl.escrow.dto.AssetRegisterDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.AssetRegister;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.AssetRegisterMapper;
import com.cdl.escrow.repository.AssetRegisterRepository;
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
public class AssetRegisterCriteriaService extends BaseSpecificationBuilder<AssetRegister> implements Serializable {

    private final transient AssetRegisterRepository buildPartnerRepository;

    private final transient AssetRegisterMapper assetRegisterMapper;

    public Page<AssetRegisterDTO> findByCriteria(AssetRegisterCriteria criteria, Pageable pageable) {
        Specification<AssetRegister> specification = createSpecification(criteria);
        return buildPartnerRepository.findAll(specification, pageable).map(assetRegisterMapper::toDto);
    }

    public Specification<AssetRegister> createSpecification(AssetRegisterCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "arDeveloperId", criteria.getArDeveloperId(), true);
                addStringFilter(cb, root, predicates, "arCifrera", criteria.getArCifrera(), true);
                addStringFilter(cb, root, predicates, "arDeveloperRegNo", criteria.getArDeveloperRegNo(), true);
                addStringFilter(cb, root, predicates, "arName", criteria.getArName(), true);
                addStringFilter(cb, root, predicates, "arMasterName", criteria.getArMasterName(), true);
                addStringFilter(cb, root, predicates, "arNameLocal", criteria.getArNameLocal(), true);
                addZonedDateTimeFilter(cb, root, predicates, "arOnboardingDate", criteria.getArOnboardingDate());
                addStringFilter(cb, root, predicates, "arContactAddress", criteria.getArContactAddress(), true);
                addStringFilter(cb, root, predicates, "arContactTel", criteria.getArContactTel(), true);
                addStringFilter(cb, root, predicates, "arPoBox", criteria.getArPoBox(), true);
                addStringFilter(cb, root, predicates, "arMobile", criteria.getArMobile(), true);
                addStringFilter(cb, root, predicates, "arFax", criteria.getArFax(), true);
                addStringFilter(cb, root, predicates, "arEmail", criteria.getArEmail(), true);
                addStringFilter(cb, root, predicates, "arLicenseNo", criteria.getArLicenseNo(), true);
                addZonedDateTimeFilter(cb, root, predicates, "arLicenseExpDate", criteria.getArLicenseExpDate());
                addStringFilter(cb, root, predicates, "arWorldCheckFlag", criteria.getArWorldCheckFlag(), true);
                addStringFilter(cb, root, predicates, "arWorldCheckRemarks", criteria.getArWorldCheckRemarks(), true);
                addBooleanFilter(cb, root, predicates, "arMigratedData", criteria.getArMigratedData());
                addStringFilter(cb, root, predicates, "arRemark", criteria.getArRemark(), true);

                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());
                // relationships

                if (criteria.getArRegulatorId() != null) {
                    Join<AssetRegister, ApplicationSetting> join = root.join("arRegulator", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getArRegulatorId());
                }

                if (criteria.getArActiveStatusId() != null) {
                    Join<AssetRegister, ApplicationSetting> join = root.join("arActiveStatus", JoinType.LEFT);
                    addLongFilterOnJoin(cb, join, predicates, "id", criteria.getArActiveStatusId());
                }

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
