package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Represents a real estate unit associated with a Capital Partner.
 * Includes details like unit number, area, and project association.
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
@Table(name = "owner_registry_unit")
public class OwnerRegistryUnit implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_unit_id_seq_gen",
            sequenceName = "owner_registry_unit_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_unit_id_seq_gen"
    )
    private Long id ;

    private String unitRefId;

    private String altUnitRefId;

    private String name;

    private Boolean isResale;

    private ZonedDateTime resaleDate;

    private String unitSysId;

    private String otherFormatUnitNo;

    private String virtualAccNo;

    private String towerName;

    private String unitPlotSize;

    private String floor;

    private String noofBedroom;

    private Boolean isModified;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnit partnerUnit;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnitType ownerRegistryUnitType;

    @ManyToOne
    @JsonIgnore
    private ManagementFirm managementFirm;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting unitStatus;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting propertyId;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting paymentPlanType;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnitBooking ownerRegistryUnitBooking;

    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private Set<ManagementFirmHistory> projectFeeHistories ;

    @OneToMany(mappedBy = "partnerUnit")
    @JsonIgnore
    private Set<OwnerRegistryUnit> childOwnerRegistryUnit;

   /* @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private transient Set<BankAccount> bankAccounts ;
*/
    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private Set<OwnerRegistry> ownerRegistries;

    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private Set<OwnerRegistryUnitPurchase> ownerRegistryUnitPurchases;

    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private Set<ProcessedFundIngress> processedFundIngresses;

    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private transient Set<PendingFundIngress> pendingFundIngresses;

    @OneToMany(mappedBy = "ownerRegistryUnit")
    @JsonIgnore
    private Set<FundEgress> fundEgresses;

    @OneToMany(mappedBy = "transferOwnerRegistryUnit")
    @JsonIgnore
    private Set<FundEgress> transferFundEgresses;
    private Boolean enabled ;

    private Boolean deleted;

}
