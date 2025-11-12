package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
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
@Table(name = "management_firm_beneficiary")
public class ManagementFirmBeneficiary implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_beneficiary_id_seq_gen",
            sequenceName = "management_firm_beneficiary_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_beneficiary_id_seq_gen"
    )
    private Long id;

    private String mfbBeneficiaryId;

    private String mfbName;

    private Double mfbContractAmount;

    private Double mfbActualLandPrice;

    private String mfbContractorName;

    private String mfbType;

    private String mfbBank;

    private String mfbSwift;

    private String mfbRoutingCode;

    private String mfbAddress;

    private String mfbBankAddress;

    private Boolean mfbIsActive;

    private Boolean mfbIsDeleted;

    private String mfbBeneAccount;

    private String mfbBeneIban;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbTransferType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbExpenseType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbVendorSubType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbContractorSubType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbInfrastructureCategory;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mfbSalesCategory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "rel_management_firm_beneficiary__management_firm",
            joinColumns = @JoinColumn(name = "management_firm_beneficiary_id"),
            inverseJoinColumns = @JoinColumn(name = "management_firm_id")
    )
   @JsonIgnore

    private Set<ManagementFirm> managementFirms = new HashSet<>();

   /* @OneToMany(mappedBy = "managementFirmBeneficiary")
    @JsonIgnore
    private transient Set<BankAccount> bankAccounts ;
*/
    @OneToMany(mappedBy = "managementFirmBeneficiary")
    @JsonIgnore
    private Set<FundEgress> fundEgresses;

    private Boolean enabled ;

    private Boolean deleted;

    // New added field

    private String mfbTradeLicenseNumber;

    private String mfbTradeLicenseIssueLocation;

    private ZonedDateTime mfbTradeLicenseExpiationDate;


}
