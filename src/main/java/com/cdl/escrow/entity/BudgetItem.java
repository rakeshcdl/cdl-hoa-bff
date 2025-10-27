package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
@Table(name = "budget_item")
public class BudgetItem implements Serializable {

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

    private String subCategoryCode;

    private String subCategoryName;

    private String subCategoryNameLocale;

    private String serviceCode;

    private String provisionalServiceCode;

    private String serviceName;

    private String serviceNameLocale;

    private Double totalBudget;

    private Double availableBudget;

    private Double utilizedBudget;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne
    @JsonIgnore
    private BudgetCategory budgetCategory;

    @OneToMany(mappedBy = "budgetItem")
    @JsonIgnore
    private Set<FundEgress> fundEgresses = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

}
