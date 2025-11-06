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
 * Represents a real estate project registered under the RERA regulation.
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

    private String reaId;

    private String reaCif;

    private String reaName;

    @Nationalized
    private String reaNameLocal;

    private String reaLocation;

    private String reaReraNumber;

    private ZonedDateTime reaStartDate;

    private ZonedDateTime reaCompletionDate;

    private String reaPercentComplete;

    private Double reaConstructionCost;

    private ZonedDateTime reaAccStatusDate;

    private ZonedDateTime reaRegistrationDate;

    private Integer reaNoOfUnits;

    private String reaRemarks;

    private String reaSpecialApproval;

    private String reaManagedBy;

    private String reaBackupUser;

    private String reaRetentionPercent;

    private String reaAdditionalRetentionPercent;

    private String reaTotalRetentionPercent;

    private ZonedDateTime reaRetentionEffectiveDate;

    private String reaManagementExpenses;

    private String reaMarketingExpenses;

    private ZonedDateTime reaAccoutStatusDate;

    private String reaTeamLeadName;

    private String reaRelationshipManagerName;

    private String reaAssestRelshipManagerName;

    private Double reaRealEstateBrokerExp;

    private Double reaAdvertisementExp;

    private String reaLandOwnerName;

    @ManyToOne
    @JsonIgnore
    private AssetRegister assetRegister;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reaStatus;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reaType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reaAccountStatus;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reaConstructionCostCurrency;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reaBlockPaymentTypes;

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
    private Set<ManagementFirmHistory> realEstateAssestFeeHistories ;

    @OneToMany(mappedBy = "managementFirm")
    @JsonIgnore
    private Set<ManagementFirmFinancialSummary> realEstateAssestFinancialSummaries ;

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
