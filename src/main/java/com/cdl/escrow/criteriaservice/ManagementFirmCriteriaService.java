package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmCriteria;
import com.cdl.escrow.dto.ManagementFirmDTO;
import com.cdl.escrow.entity.ManagementFirm;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmMapper;
import com.cdl.escrow.repository.ManagementFirmRepository;
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
public class ManagementFirmCriteriaService extends BaseSpecificationBuilder<ManagementFirm> implements Serializable {

    private final transient ManagementFirmRepository managementFirmRepository;

    private final transient ManagementFirmMapper managementFirmMapper;

    public Page<ManagementFirmDTO> findByCriteria(ManagementFirmCriteria criteria, Pageable pageable) {
        Specification<ManagementFirm> specification = cmfteSpecification(criteria);
        return managementFirmRepository.findAll(specification, pageable).map(managementFirmMapper::toDto);
    }

    private Specification<ManagementFirm> cmfteSpecification(ManagementFirmCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "mfId", criteria.getMfId(), true);
                addStringFilter(cb, root, predicates, "mfCif", criteria.getMfCif(), true);
                addStringFilter(cb, root, predicates, "mfName", criteria.getMfName(), true);
                addStringFilter(cb, root, predicates, "mfNameLocal", criteria.getMfNameLocal(), true);
                addStringFilter(cb, root, predicates, "mfLocation", criteria.getMfLocation(), true);
                addStringFilter(cb, root, predicates, "mfReraNumber", criteria.getMfReraNumber(), true);
                addZonedDateTimeFilter(cb, root, predicates, "mfStartDate", criteria.getMfStartDate());
                addZonedDateTimeFilter(cb, root, predicates, "mfCompletionDate", criteria.getMfCompletionDate());
                addStringFilter(cb, root, predicates, "mfPercentComplete", criteria.getMfPercentComplete(), true);
                addDoubleFilter(cb, root, predicates, "mfConstructionCost", criteria.getMfConstructionCost());
                addZonedDateTimeFilter(cb, root, predicates, "mfAccStatusDate", criteria.getMfAccStatusDate());
                addZonedDateTimeFilter(cb, root, predicates, "mfRegistrationDate", criteria.getMfRegistrationDate());
                addIntegerFilter(cb, root, predicates, "mfNoOfUnits", criteria.getMfNoOfUnits());
                addStringFilter(cb, root, predicates, "mfRemarks", criteria.getMfRemarks(), true);
                addStringFilter(cb, root, predicates, "mfSpecialApproval", criteria.getMfSpecialApproval(), true);
                addStringFilter(cb, root, predicates, "mfManagedBy", criteria.getMfManagedBy(), true);
                addStringFilter(cb, root, predicates, "mfBackupUser", criteria.getMfBackupUser(), true);
                addStringFilter(cb, root, predicates, "mfRetentionPercent", criteria.getMfRetentionPercent(), true);
                addStringFilter(cb, root, predicates, "mfAdditionalRetentionPercent", criteria.getMfAdditionalRetentionPercent(), true);
                addStringFilter(cb, root, predicates, "mfTotalRetentionPercent", criteria.getMfTotalRetentionPercent(), true);
                addZonedDateTimeFilter(cb, root, predicates, "mfRetentionEffectiveDate", criteria.getMfRetentionEffectiveDate());
                addStringFilter(cb, root, predicates, "mfManagementExpenses", criteria.getMfManagementExpenses(), true);
                addStringFilter(cb, root, predicates, "mfMarketingExpenses", criteria.getMfMarketingExpenses(), true);
                addZonedDateTimeFilter(cb, root, predicates, "mfAccoutStatusDate", criteria.getMfAccoutStatusDate());
                addStringFilter(cb, root, predicates, "mfTeamLeadName", criteria.getMfTeamLeadName(), true);
                addStringFilter(cb, root, predicates, "mfRelationshipManagerName", criteria.getMfRelationshipManagerName(), true);
                addStringFilter(cb, root, predicates, "mfAssestRelshipManagerName", criteria.getMfAssestRelshipManagerName(), true);
              //  addDoubleFilter(cb, root, predicates, "mfMflEstateBrokerExp", criteria.get());
                addDoubleFilter(cb, root, predicates, "mfAdvertisementExp", criteria.getMfAdvertisementExp());
                addStringFilter(cb, root, predicates, "mfLandOwnerName", criteria.getMfLandOwnerName(), true);

                addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
                addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());

            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
