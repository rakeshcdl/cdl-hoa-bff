package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the designated beneficiary of a Real Estate Asset,
 * typically a contractor, vendor, or developer receiving escrow disbursements.
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

    private String reabBeneficiaryId;

    private String reabName;

    private Double reabContractAmount;

    private Double reabActualLandPrice;

    private String reabContractorName;

    private String reabType;

    private String reabBank;

    private String reabSwift;

    private String reabRoutingCode;

    private String reabAddress;

    private String reabBankAddress;

    private Boolean reabIsActive;

    private Boolean reabIsDeleted;

    private String reabBeneAccount;

    private String reabBeneIban;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabTranferType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabExpenseType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabVendorSubType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabContractorSubType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabInfrastructureCategory;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting reabSalesCategory;

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
}
