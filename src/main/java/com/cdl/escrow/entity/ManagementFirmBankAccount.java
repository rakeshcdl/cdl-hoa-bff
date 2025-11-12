package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Represents the bank account linked to a Real Estate Asset,
 * used for escrow disbursements or project-related financial operations.
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
@Table(name = "management_firm_bank_account")
public class ManagementFirmBankAccount implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_bank_account_id_seq_gen",
            sequenceName = "management_firm_bank_account_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_bank_account_id_seq_gen"
    )
    private Long id ;


    private String accountType; // TRUST, RETENTION, SUB_CONSTRUCTION, CORPORATE


    private String accountNumber;


    private String ibanNumber;


    private ZonedDateTime dateOpened;


    private String accountTitle;


    private String currencyCode;

    private Boolean isValidated ;


    private ZonedDateTime createdAt = ZonedDateTime.now();

    private ZonedDateTime updatedAt = ZonedDateTime.now();


    @ManyToOne
    @JsonIgnore
    private ManagementFirm managementFirm;

    private Boolean enabled ;

    private Boolean deleted;

    // new added

    private Boolean interestBearing;

}
