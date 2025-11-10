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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "owner_registry_contact_detail")
public class OwnerRegistryContactDetail implements Serializable {

    @Id
    @SequenceGenerator(
            name = "owner_registry_contact_detail_id_seq_gen",
            sequenceName = "owner_registry_contact_detail_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "owner_registry_contact_detail_id_seq_gen"
    )
    private Long id ;

    private String jointwnDetailsContactName;

    private String jointownDetailsFirstName;

    private String jointownDetailsLastName;

    private String jointownDetailsContactTelCode;

    private String jointownDetailsContactTelNo;

    private String jointownDetailsCountryMobCode;

    private String jointownDetailsContactMobNo;

    private String jointownDetailsContactEmail;

    private String jointownDetailsContactAddress;

    private String jointownDetailsContactAddressLine1;

    private String jointownDetailsContactAddressLine2;

    private String jointownDetailsContactPoBox;

    private String jointownDetailsContactFaxNo;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

    @ManyToOne
    @JsonIgnore
    private OwnerRegistry ownerRegistry;

}
