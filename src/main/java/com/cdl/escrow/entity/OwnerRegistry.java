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
@Table(name = "owner_registry")
public class OwnerRegistry implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_id_seq_gen",
            sequenceName = "owner_registry_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_id_seq_gen"
    )
    private Long id ;

    private String ownerRegistryId;

    private String ownerRegistryName;

    private String ownerRegistryMiddleName;

    private String ownerRegistryLastName;

    private Float ownerRegistryOwnershipPercentage;

    private String ownerRegistryIdNo;

    private String ownerRegistryTelephoneNo;

    private String ownerRegistryMobileNo;

    private String ownerRegistryEmail;

    private Integer ownerRegistryOwnerNumber;

    private Boolean isCurrent;

    private ZonedDateTime idExpiryDate;

    private String ownerRegistryLocaleName;


    //New field added

    private String ownerName;

    private String ownerLocalName;

    private String ownerType;

    private String ownerIdType;

    private String ownerIdNumber;

    private String ownerNationality;

    private String ownerMobileNumber;

    private String ownerEmailAddress;

    private String ownerOwnershipShare;

    private String reservedShare;

    private String ownerMollakIdNumber;

    private String ownerFloor;

    private String ownerNoOfBedrooms;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting documentType;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting countryOption;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting investorType;

    @OneToMany(mappedBy = "ownerRegistry")
    @JsonIgnore
    private Set<OwnerRegistryBankInfo> ownerRegistryBankInfos = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnit ownerRegistryUnit;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @OneToMany(mappedBy = "ownerRegistry")
    @JsonIgnore
    private Set<OwnerRegistryContactDetail> ownerRegistryContactDetails = new HashSet<>();
}
