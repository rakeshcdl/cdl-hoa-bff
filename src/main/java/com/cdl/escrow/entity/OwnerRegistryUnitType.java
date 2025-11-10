package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

/**
 * Defines the type of unit available to Capital Partners,
 * such as Apartment, Villa, Commercial Office, etc.
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
@Table(name = "owner_registry_unit_type")
public class OwnerRegistryUnitType implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_unit_type_id_seq_gen",
            sequenceName = "owner_registry_unit_type_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_unit_type_id_seq_gen"
    )
    private Long id ;

    private String ownutName;

    @Lob
    @Column(name = "ownut_icon" , columnDefinition = "TEXT")
    private byte[] ownutIcon;

    private String ownutIconContentType;

    private Boolean ownutIsStandalone;

    private String ownUnitTypePrefix;

    private String ownutExcelFormula;

    private String ownutJsFormula;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnitType capitalPartnerParentUnitType;

    @OneToMany(mappedBy = "capitalPartnerParentUnitType")
    @JsonIgnore
    private Set<OwnerRegistryUnitType> capitalPartnerChildrenUnitType ;

    @OneToMany(mappedBy = "ownerRegistryUnitType")
    @JsonIgnore
    private Set<OwnerRegistryUnit> ownerRegistryUnits;

    private Boolean enabled ;

    private Boolean deleted;
}
