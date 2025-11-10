package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryUnitPurchaseDTO implements Serializable {
    private Long id ;

    private ZonedDateTime ownuPurchaseDate;

    private Double ownupSaleRate;

    private Double ownuPurchasePrice;

    private Double ownupUnitRegistrationFee;

    private String ownupAgentName;

    private String ownupAgentId;

    private Double ownupGrossSaleprice;

    private Boolean ownupVatApplicable;

    private String ownupDeedNo;

    private String ownupAgreementNo;

    private ZonedDateTime ownupAgreementDate;

    private Boolean ownupSalePurchaseAgreement;

    private Boolean ownupWorldCheck;

    private Double ownupAmtPaidToDevInEscorw;

    private Double ownupAmtPaidToDevOutEscorw;

    private Double ownupTotalAmountPaid;

    private String ownupUnitIban;

    private Boolean ownupOqood;

    private Boolean ownupOqoodPaid;

    private String ownupOqoodAmountPaid;

    private String ownupUnitAreaSize;

    private String ownupForfeitAmount;

    private String ownupDldAmount;

    private String ownupRefundAmount;

    private String ownupRemarks;

    private String ownupTransferredAmount;

    private String ownupUnitNoOtherFormat;

    private Double ownupSalePrice;

    private Boolean ownupProjectPaymentPlan;

    private Boolean ownupReservationBookingForm;

    private Boolean ownupModificationFeeNeeded;

    private ApplicationSettingDTO ownupCreditCurrencyDTO;

    private ApplicationSettingDTO ownuPurchasePriceCurrencyDTO;

    private OwnerRegistryUnitDTO ownerRegistryUnitDTO;

    private Boolean deleted ;

    private boolean enabled ;
}
