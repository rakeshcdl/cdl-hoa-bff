package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

/**
 * Represents the booking or reservation of a unit by a Capital Partner.
 * Precedes full purchase and may include booking amount and validity.
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
@Table(name = "owner_registry_unit_booking")
public class OwnerRegistryUnitBooking implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_unit_booking_id_seq_gen",
            sequenceName = "owner_registry_unit_booking_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_unit_booking_id_seq_gen"
    )
    private Long id ;

    private Double ownubAmountPaid;

    private Double ownubAreaSize;

    private Double ownubForFeitAmount;

    private Double ownubDldAmount;

    private Double ownubRefundAmount;

    @Lob
    @Column(name = "cupb_remarks" , columnDefinition = "TEXT")
    private String ownubRemarks;

    private Double ownubTransferredAmount;

    @OneToMany(mappedBy = "ownerRegistryUnitBooking")
    @JsonIgnore
    private Set<OwnerRegistryUnit> ownerRegistryUnits;
    private Boolean enabled ;

    private Boolean deleted;
}
