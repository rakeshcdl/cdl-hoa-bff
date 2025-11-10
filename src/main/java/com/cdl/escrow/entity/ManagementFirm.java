package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a mfl estate project registered under the RERA regulation.
 * Each project is associated with a developer and may have multiple funding and disbursement records.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "management_firm")
public class ManagementFirm implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_id_seq_gen",
            sequenceName = "management_firm_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_id_seq_gen"
    )
    private Long id;

    private String mfId;

    private String mfCif;

    private String mfName;

    @Nationalized
    private String mfNameLocal;

    private String mfLocation;

    private String mfReraNumber;

    private ZonedDateTime mfStartDate;

    private ZonedDateTime mfCompletionDate;

    private String mfPercentComplete;

    private Double mfConstructionCost;

    private ZonedDateTime mfAccStatusDate;

    private ZonedDateTime mfRegistrationDate;

    private Integer mfNoOfUnits;

    private String mfRemarks;

    private String mfSpecialApproval;

    private String mfManagedBy;

    private String mfBackupUser;

    private String mfRetentionPercent;

    private String mfAdditionalRetentionPercent;

    private String mfTotalRetentionPercent;

    private ZonedDateTime mfRetentionEffectiveDate;

    private String mfManagementExpenses;

    private String mfMarketingExpenses;

    private ZonedDateTime mfAccoutStatusDate;

    private String mfTeamLeadName;

    private String mfRelationshipManagerName;

    private String mfAssestRelshipManagerName;

    private Double mfRealEstateBrokerExp;

    private Double mfAdvertisementExp;

    private String mfLandOwnerName;

    @ManyToOne
    @JsonIgnore
    private AssetRegister assetRegister;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfStatus;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfAccountStatus;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfConstructionCostCurrency;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfBlockPaymentTypes;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private transient Set<BankAccount> bankAccounts ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmBankAccount> managementFirmBankAccounts;


    @ManyToMany(mappedBy = "managementFirms" , fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<ManagementFirmBeneficiary> managementFirmBeneficiaries = new HashSet<>();

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmClosure> managementFirmClosures;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmFee> managementFirmFees;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmHistory> mflFeeHistories ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmFinancialSummary> mflFinancialSummaries ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<OwnerRegistryUnit> ownerRegistryUnits;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ProcessedFundIngress> processedFundIngresses ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private transient Set<PendingFundIngress> pendingFundIngresses;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<FundEgress> fundEgresses ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<SuretyBond> suretyBonds;
    private Boolean enabled ;

    private Boolean deleted;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmPaymentPlan> managementFirmPaymentPlans;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    // Budget

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<Budget> budgets = new HashSet<>();
}
