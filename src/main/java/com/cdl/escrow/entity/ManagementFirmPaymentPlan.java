package com.cdl.escrow.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "management_firm_payment_plan")
public class ManagementFirmPaymentPlan implements Serializable {

    @Id
    @SequenceGenerator(
            name = "management_firm_payment_plan_id_seq_gen",
            sequenceName = "management_firm_payment_plan_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_payment_plant_id_seq_gen"
    )
    private Long id;

    private Integer reappInstallmentNumber;

    private Double reappInstallmentPercentage;

    private Double reappProjectCompletionPercentage;

    // Many installments belong to one Real Estate Assets
    @ManyToOne(fetch = FetchType.LAZY)
    private ManagementFirm managementFirm;

    private Boolean enabled ;

    private Boolean deleted;
}
