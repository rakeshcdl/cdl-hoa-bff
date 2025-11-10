package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "owner_registry_bank_info")
public class OwnerRegistryBankInfo implements Serializable {
    @Id
    @SequenceGenerator(
            name = "owner_registry_bank_info_id_seq_gen",
            sequenceName = "owner_registry_bank_info_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_bank_info_id_seq_gen"
    )
    private Long id ;

    private String ownbiPayeeName;

    private String ownbiPayeeAddress;

    private String ownbiBankName;

    private String ownbiBankAddress;

    private String ownbiBicCode;

    private String ownbiBeneRoutingCode;

    private String ownbiAccountNumber;

    private String ownbiIban;

   /* @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private  BankAccount bankAccount;
*/
    @ManyToOne
    @JsonIgnore
    private OwnerRegistry ownerRegistry;

    @ManyToOne
    @JsonIgnore
    private ApplicationSetting payMode;
    private Boolean enabled ;

    private Boolean deleted;
}
