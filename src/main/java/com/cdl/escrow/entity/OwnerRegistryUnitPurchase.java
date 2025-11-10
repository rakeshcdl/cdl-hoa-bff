package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Represents a unit purchase transaction by a Capital Partner.
 * Tracks payment status, ownership, and financial details.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "owner_registry_unit_purchase")
public class OwnerRegistryUnitPurchase implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_unit_purchase_id_seq_gen",
            sequenceName = "owner_registry_unit_purchase_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_unit_purchase_id_seq_gen"
    )
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

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting ownupCreditCurrency;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting ownuPurchasePriceCurrency;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnit ownerRegistryUnit;

    private Boolean enabled ;

    private Boolean deleted;
}
