package com.cdl.escrow.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "owner_registry_payment_plan")
public class OwnerRegistryPaymentPlan implements Serializable {

    @Id
    @SequenceGenerator(
            name = "owner_registry_payment_plan_id_seq_gen",
            sequenceName = "owner_registry_payment_plan_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_payment_plant_id_seq_gen"
    )
    private Long id;

    private Integer ownppInstallmentNumber;

    private ZonedDateTime ownppInstallmentDate;

    private Double ownppBookingAmount;

    // Many installments belong to one capital Partner
    @ManyToOne(fetch = FetchType.LAZY)
    private OwnerRegistry ownerRegistry;
    private Boolean enabled ;

    private Boolean deleted;
}
