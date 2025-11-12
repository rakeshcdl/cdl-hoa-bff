package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.ZonedDateTime;
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
@Table(name = "budget_category")
public class BudgetCategory implements Serializable {

    @Id
    @SequenceGenerator(
            name = "budget_category_id_seq_gen",
            sequenceName = "budget_category_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_category_seq_gen"
    )
    private Long id;

    private Integer serviceChargeGroupId;

    private String serviceChargeGroupName;

    private String serviceChargeGroupNameLocale;

    private String usageLocale;

    private String serviceName;

    private String serviceCode;

    private String provisionalBudgetCode;

    private Integer chargeTypeId;

    private String chargeType;

    private String usage;

    private ZonedDateTime budgetPeriodFrom;

    private ZonedDateTime budgetPeriodTo;

    private String budgetPeriodTitle;

    private String categoryCode;

    private String categoryName;

    private String categoryNameLocale;

    private String categorySubCode;

    private String categorySubName;

    private String categorySubToSubCode;

    private String categorySubToSubName;

    private Double vatAmount;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne
    @JsonIgnore
    private Budget budget;

    @OneToMany(mappedBy = "budgetCategory")
    @JsonIgnore
    private Set<FundEgress> fundEgresses = new HashSet<>();

    @OneToMany(mappedBy = "budgetCategory")
    @JsonIgnore
    private Set<BudgetItem> budgetItems = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;
}
