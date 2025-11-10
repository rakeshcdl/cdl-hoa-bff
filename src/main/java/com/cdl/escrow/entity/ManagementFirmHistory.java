package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Maintains the historical record of fee changes or payments for a Real Estate Asset.
 * Useful for auditing and financial tracking.
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
@Table(name = "management_firm_fee_history")
public class ManagementFirmHistory implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_fee_history_id_seq_gen",
            sequenceName = "management_firm_fee_history_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_fee_history_id_seq_gen"
    )
    private Long id;

    private Double mffhAmount;

    private Double mffhTotalAmount;

    private Double mffhVatPercentage;

    private ZonedDateTime mffhTransactionDate;

    private Boolean mffhSuccess;

    private Boolean mffhStatus;

    private String mfhfRemark;


    @Column(name = "mffh_fee_response", columnDefinition = "TEXT")
    private String mffhFeeResponse;

    private String mffhResponseStatus;

    private String mffhSpecialField1;

    private String mffhSpecialField2;

    private String mffhSpecialField3;

    private String mffhSpecialField4;

    private String mffhSpecialField5;


    @Column(name = "mffh_fee_request_body", columnDefinition = "TEXT")
    private String mffhFeeRequestBody;

    @ManyToOne
    @JsonIgnore
    private ManagementFirmFee managementFirmFee;

    @ManyToOne
    @JsonIgnore
    private ManagementFirm managementFirm;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistryUnit ownerRegistryUnit;

    private Boolean enabled ;

    private Boolean deleted;

}
