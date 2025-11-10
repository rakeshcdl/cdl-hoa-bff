package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.ManagementFirmFinancialSummaryCriteria;
import com.cdl.escrow.dto.ManagementFirmFinancialSummaryDTO;
import com.cdl.escrow.entity.ManagementFirmFinancialSummary;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.ManagementFirmFinancialSummaryMapper;
import com.cdl.escrow.repository.ManagementFirmFinancialSummaryRepository;
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
public class ManagementFirmFinancialSummaryCriteriaService extends BaseSpecificationBuilder<ManagementFirmFinancialSummary> implements Serializable {

    private final transient ManagementFirmFinancialSummaryRepository managementFirmFinancialSummaryRepository;

    private final transient ManagementFirmFinancialSummaryMapper managementFirmFinancialSummaryMapper;

    public Page<ManagementFirmFinancialSummaryDTO> findByCriteria(ManagementFirmFinancialSummaryCriteria criteria, Pageable pageable) {
        Specification<ManagementFirmFinancialSummary> specification = cmfteSpecification(criteria);
        return managementFirmFinancialSummaryRepository.findAll(specification, pageable).map(managementFirmFinancialSummaryMapper::toDto);
    }

    private Specification<ManagementFirmFinancialSummary> cmfteSpecification(ManagementFirmFinancialSummaryCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addStringFilter(cb, root, predicates, "mffsEstRevenue", criteria.getMffsEstRevenue(), true);
                addDoubleFilter(cb, root, predicates, "mffsEstConstructionCost", criteria.getMffsActualConstructionCost());
                addDoubleFilter(cb, root, predicates, "mffsEstProjectMgmtExpense", criteria.getMffsActualProjectMgmtExpense());
                addDoubleFilter(cb, root, predicates, "mffsEstLandCost", criteria.getMffsEstLandCost());
                addDoubleFilter(cb, root, predicates, "mffsEstMarketingExpense", criteria.getMffsEstMarketingExpense());
                addZonedDateTimeFilter(cb, root, predicates, "mffsEstimatedDate", criteria.getMffsEstimatedDate());
                addStringFilter(cb, root, predicates, "mffsEstExceptionalCapVal", criteria.getMffsEstExceptionalCapVal(), true);
                addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", criteria.getMffsActualSoldValue());
                addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", criteria.getMffsActualConstructionCost());
                addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", criteria.getMffsActualInfraCost());
                addDoubleFilter(cb, root, predicates, "mffsActualLandCost", criteria.getMffsActualLandCost());
                addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", criteria.getMffsActualMarketingExp());
                addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", criteria.getMffsActualProjectMgmtExpense());
                addZonedDateTimeFilter(cb, root, predicates, "mffsActualDate", criteria.getMffsActualDate());
                addStringFilter(cb, root, predicates, "mffsActualexceptCapVal", criteria.getMffsActualexceptCapVal(), true);

                if (criteria.getManagementFirmId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "managementFirmId", "id", criteria.getManagementFirmId());
                }

           /* addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);

            addDoubleFilter(cb, root, predicates, "mffsActualSoldValue", this.mffsActualSoldValue);
            addDoubleFilter(cb, root, predicates, "mffsActualConstructionCost", this.mffsActualConstructionCost);
            addDoubleFilter(cb, root, predicates, "mffsActualInfraCost", this.mffsActualInfraCost);
            addDoubleFilter(cb, root, predicates, "mffsActualLandCost", this.mffsActualLandCost);
            addDoubleFilter(cb, root, predicates, "mffsActualMarketingExp", this.mffsActualMarketingExp);
            addDoubleFilter(cb, root, predicates, "mffsActualProjectMgmtExpense", this.mffsActualProjectMgmtExpense);*/
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
