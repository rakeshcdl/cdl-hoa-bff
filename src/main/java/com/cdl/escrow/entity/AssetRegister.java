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
@Table(name = "asset_register")
public class AssetRegister implements Serializable {
    @Id
    @SequenceGenerator(
            name = "asset_register_id_seq_gen",
            sequenceName = "asset_register_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "asset_register_id_seq_gen"
    )
    private Long id;

    private String arDeveloperId;

    private String arCifrera;

    private String arDeveloperRegNo;

    private String arName;

    private String arMasterName;

    @Nationalized
    private String arNameLocal;

    private ZonedDateTime arOnboardingDate;

    private String arContactAddress;

    private String arContactTel;

    private String arPoBox;

    private String arMobile;

    private String arFax;

    private String arEmail;

    private String arLicenseNo;

    private ZonedDateTime arLicenseExpDate;

    private String arWorldCheckFlag;

    private String arWorldCheckRemarks;

    private Boolean arMigratedData;
    private Boolean enabled ;

    private Boolean deleted;

    private String arProjectName;

    private String arCompanyNumber;

    private String arMasterCommunity;

    private String arMasterDeveloper;


    @Lob
    @Column(name = "bp_remark", columnDefinition = "TEXT")
    private String arRemark;

    @ManyToOne
    private ApplicationSetting arRegulator;

    @ManyToOne
    private ApplicationSetting arActiveStatus;

    @ManyToMany(mappedBy = "assetRegisters", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<BuildPartnerBeneficiary> buildPartnerBeneficiaries = new HashSet<>();

    @OneToMany(mappedBy = "assetRegister")
    private Set<AssetRegisterContact> assetRegisterContacts;

    @OneToMany(mappedBy = "assetRegister")
    @JsonIgnore
    private Set<ManagementFirmFinancialSummary> realEstateAssestFinancialSummaries ;

    @OneToMany(mappedBy = "assetRegister")
    @JsonIgnore
    private Set<FundEgress> fundEgresses;
/*

    @OneToMany(mappedBy = "buildPartner")
    @JsonIgnore
    private transient Set<BankAccount> bankAccounts ;
*/

    @OneToMany(mappedBy = "assetRegister")
    private Set<BuildPartnerFees> buildPartnerFees;

    @OneToMany(mappedBy = "assetRegister")
    private Set<BuildPartnerAccount> buildPartnerAccounts;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    //Budget
    @OneToMany(mappedBy = "assetRegister")
    @JsonIgnore
    private Set<Budget> budgets = new HashSet<>();
}
