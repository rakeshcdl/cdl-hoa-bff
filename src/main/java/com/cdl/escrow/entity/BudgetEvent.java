package com.cdl.escrow.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "budget_event")
public class BudgetEvent implements Serializable {

    @Id
    @SequenceGenerator(
            name = "budget_eventy_id_seq_gen",
            sequenceName = "budget_event_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_event_seq_gen"
    )
    private Long id;

    private String eventId;

    private String urc;

    private ZonedDateTime timeStamp;

    private String syncType;

    private String propertyGroupId;

    private String periodCode;

    private String managementCompanyId;

    private Long acknowledgeRef;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;
}
