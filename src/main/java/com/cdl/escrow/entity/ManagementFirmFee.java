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
 * Represents a fee associated with a Real Estate Asset,
 * such as government registration, administrative, or service fees.
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
@Table(name = "management_firm_fee")
public class ManagementFirmFee implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_fee_id_seq_gen",
            sequenceName = "management_firm_fee_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_fee_id_seq_gen"
    )
    private Long id;

    private Double mffAmount;

    private Double mffDebitAmount;

    private Double mffTotalAmount;

    private ZonedDateTime mffCalender;

    private ZonedDateTime mffCollectionDate;

    private ZonedDateTime mffNextRecoveryDate;

    private Double mffVatPercentage;

    private Boolean mffCollected;

    private Double mffFeePercentage;



    @ManyToOne
    @JsonIgnore
    private ManagementFirm managementFirm;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mffCategory;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mffCurrency;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting mffFrequency;

    @ManyToOne
    private ApplicationSetting mffAccountType;


    @OneToMany(mappedBy = "managementFirmFee")
    @JsonIgnore
    private Set<ManagementFirmHistory> mfFeeHistories ;

    private Boolean enabled ;

    private Boolean deleted;
}
