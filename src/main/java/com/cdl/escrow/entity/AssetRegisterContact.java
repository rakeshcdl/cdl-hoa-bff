package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

/**
 * Represents the contact details for a Build Partner (developer),
 * including email, phone, and registered business address.
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
@Table(name = "asset_register_contact")
public class AssetRegisterContact implements Serializable {
    @Id
    @SequenceGenerator(
            name = "asset_register_contact_id_seq_gen",
            sequenceName = "asset_register_contact_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "asset_register_contact_id_seq_gen"
    )
    private Long id;

    private String arcContactName;

    private String arcFirstName;

    private String arcLastName;

    private String arcContactTelCode;

    private String arcContactTelNo;

    private String arcCountryMobCode;

    private String arcContactMobNo;

    private String arcContactEmail;

    private String arcContactAddress;

    private String arcContactAddressLine1;

    private String arcContactAddressLine2;

    private String arcContactPoBox;

    private String arcContactFaxNo;

    @ManyToOne
    @JsonIgnore
    private AssetRegister assetRegister;

    private Boolean enabled ;

    private Boolean deleted;
}
