package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OwnerRegistryUnitPurchaseCriteria implements Serializable {

    private LongFilter id ;

    private ZonedDateTimeFilter ownuPurchaseDate;

    private DoubleFilter ownupSaleRate;

    private DoubleFilter ownuPurchasePrice;

    private DoubleFilter ownupUnitRegistrationFee;

    private StringFilter ownupAgentName;

    private StringFilter ownupAgentId;

    private DoubleFilter ownupGrossSaleprice;

    private BooleanFilter ownupVatApplicable;

    private StringFilter ownupDeedNo;

    private StringFilter ownupAgreementNo;

    private ZonedDateTimeFilter ownupAgreementDate;

    private BooleanFilter ownupSalePurchaseAgreement;

    private BooleanFilter ownupWorldCheck;

    private DoubleFilter ownupAmtPaidToDevInEscorw;

    private DoubleFilter ownupAmtPaidToDevOutEscorw;

    private DoubleFilter ownupTotalAmountPaid;

    private StringFilter ownupUnitIban;

    private BooleanFilter ownupOqood;

    private BooleanFilter ownupOqoodPaid;

    private StringFilter ownupOqoodAmountPaid;

    private StringFilter ownupUnitAreaSize;

    private StringFilter ownupForfeitAmount;

    private StringFilter ownupDldAmount;

    private StringFilter ownupRefundAmount;

    private StringFilter ownupRemarks;

    private StringFilter ownupTransferredAmount;

    private StringFilter ownupUnitNoOtherFormat;

    private DoubleFilter ownupSalePrice;

    private BooleanFilter ownupProjectPaymentPlan;

    private BooleanFilter ownupReservationBookingForm;

    private LongFilter ownupCreditCurrencyId;

    private LongFilter ownuPurchasePriceCurrencyId;

    private LongFilter capitalPartnerUnitId;
}
