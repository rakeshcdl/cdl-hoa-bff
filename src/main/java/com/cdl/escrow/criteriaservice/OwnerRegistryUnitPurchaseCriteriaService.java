package com.cdl.escrow.criteriaservice;


import com.cdl.escrow.criteria.OwnerRegistryUnitPurchaseCriteria;
import com.cdl.escrow.dto.OwnerRegistryUnitPurchaseDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitPurchase;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.OwnerRegistryUnitPurchaseMapper;
import com.cdl.escrow.repository.OwnerRegistryUnitPurchaseRepository;
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
public class OwnerRegistryUnitPurchaseCriteriaService extends BaseSpecificationBuilder<OwnerRegistryUnitPurchase> implements Serializable {

    private final transient OwnerRegistryUnitPurchaseRepository ownerRegistryUnitPurchaseRepository;

    private final transient OwnerRegistryUnitPurchaseMapper ownerRegistryUnitPurchaseMapper;

    public Page<OwnerRegistryUnitPurchaseDTO> findByCriteria(OwnerRegistryUnitPurchaseCriteria criteria, Pageable pageable) {
        Specification<OwnerRegistryUnitPurchase> specification = createSpecification(criteria);
        return ownerRegistryUnitPurchaseRepository.findAll(specification, pageable).map(ownerRegistryUnitPurchaseMapper::toDto);
    }


    public Specification<OwnerRegistryUnitPurchase> createSpecification(OwnerRegistryUnitPurchaseCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(criteria!=null) {
                // Long Filters
                addLongFilter(cb, root, predicates, "id", criteria.getId());
                addLongFilter(cb, root, predicates, "ownupCreditCurrencyId", criteria.getOwnupCreditCurrencyId());
                addLongFilter(cb, root, predicates, "ownuPurchasePriceCurrencyId", criteria.getOwnuPurchasePriceCurrencyId());
                //addLongFilter(cb, root, predicates, "capitalPartnerUnitId", criteria.getCapitalPartnerUnitId());

                // ZonedDateTime Filters
                addZonedDateTimeFilter(cb, root, predicates, "ownuPurchaseDate", criteria.getOwnuPurchaseDate());
                addZonedDateTimeFilter(cb, root, predicates, "ownupAgreementDate", criteria.getOwnupAgreementDate());

                // Double Filters
                addDoubleFilter(cb, root, predicates, "ownupSaleRate", criteria.getOwnupSaleRate());
                addDoubleFilter(cb, root, predicates, "ownuPurchasePrice", criteria.getOwnuPurchasePrice());
                addDoubleFilter(cb, root, predicates, "ownupUnitRegistrationFee", criteria.getOwnupUnitRegistrationFee());
                addDoubleFilter(cb, root, predicates, "ownupGrossSaleprice", criteria.getOwnupGrossSaleprice());
                addDoubleFilter(cb, root, predicates, "ownupAmtPaidToDevInEscorw", criteria.getOwnupAmtPaidToDevInEscorw());
                addDoubleFilter(cb, root, predicates, "ownupAmtPaidToDevOutEscorw", criteria.getOwnupAmtPaidToDevOutEscorw());
                addDoubleFilter(cb, root, predicates, "ownupTotalAmountPaid", criteria.getOwnupTotalAmountPaid());
                addDoubleFilter(cb, root, predicates, "ownupSalePrice", criteria.getOwnupSalePrice());

                // String Filters
                addStringFilter(cb, root, predicates, "ownupAgentName", criteria.getOwnupAgentName(), true);
                addStringFilter(cb, root, predicates, "ownupAgentId", criteria.getOwnupAgentId(), true);
                addStringFilter(cb, root, predicates, "ownupDeedNo", criteria.getOwnupDeedNo(), true);
                addStringFilter(cb, root, predicates, "ownupAgreementNo", criteria.getOwnupAgreementNo(), true);
                addStringFilter(cb, root, predicates, "ownupUnitIban", criteria.getOwnupUnitIban(), true);
                addStringFilter(cb, root, predicates, "ownupOqoodAmountPaid", criteria.getOwnupOqoodAmountPaid(), true);
                addStringFilter(cb, root, predicates, "ownupUnitAreaSize", criteria.getOwnupUnitAreaSize(), true);
                addStringFilter(cb, root, predicates, "ownupForfeitAmount", criteria.getOwnupForfeitAmount(), true);
                addStringFilter(cb, root, predicates, "ownupDldAmount", criteria.getOwnupDldAmount(), true);
                addStringFilter(cb, root, predicates, "ownupRefundAmount", criteria.getOwnupRefundAmount(), true);
                addStringFilter(cb, root, predicates, "ownupRemarks", criteria.getOwnupRemarks(), true);
                addStringFilter(cb, root, predicates, "ownupTransferredAmount", criteria.getOwnupTransferredAmount(), true);
                addStringFilter(cb, root, predicates, "ownupUnitNoOtherFormat", criteria.getOwnupUnitNoOtherFormat(), true);

                // Boolean Filters
                addBooleanFilter(cb, root, predicates, "ownupVatApplicable", criteria.getOwnupVatApplicable());
                addBooleanFilter(cb, root, predicates, "ownupSalePurchaseAgreement", criteria.getOwnupSalePurchaseAgreement());
                addBooleanFilter(cb, root, predicates, "ownupWorldCheck", criteria.getOwnupWorldCheck());
                addBooleanFilter(cb, root, predicates, "ownupOqood", criteria.getOwnupOqood());
                addBooleanFilter(cb, root, predicates, "ownupOqoodPaid", criteria.getOwnupOqoodPaid());
                addBooleanFilter(cb, root, predicates, "ownupProjectPaymentPlan", criteria.getOwnupProjectPaymentPlan());
                addBooleanFilter(cb, root, predicates, "ownupReservationBookingForm", criteria.getOwnupReservationBookingForm());

                // Relation Join
                if (criteria.getCapitalPartnerUnitId() != null) {
                    addLongFilterForJoin(cb, root, predicates, "ownerRegistryUnit", "id", criteria.getCapitalPartnerUnitId());
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
