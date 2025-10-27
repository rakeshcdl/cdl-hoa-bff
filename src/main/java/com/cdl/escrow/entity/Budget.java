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
@Table(name = "budget")
public class Budget implements Serializable {

    @Id
    @SequenceGenerator(
            name = "budget_id_seq_gen",
            sequenceName = "budget_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "budget_seq_gen"
    )
    private Long id;

    private String budgetId;

    private Boolean isActive;

    private String budgetPeriodCode;

    private Integer propertyGroupId;

    private String propertyManagerEmail;

    private String masterCommunityName;

    private String masterCommunityNameLocale;

    private String createdBy;

    private Boolean enabled ;

    private Boolean deleted;

    @ManyToOne
    @JsonIgnore
    private BuildPartner buildPartner;

    @ManyToOne
    @JsonIgnore
    private RealEstateAssest realEstateAssest;

    @OneToMany(mappedBy = "budget")
    @JsonIgnore
    private Set<FundEgress> fundEgresses = new HashSet<>();

    @OneToMany(mappedBy = "budget")
    @JsonIgnore
    private Set<BudgetCategory> budgetCategories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskStatus taskStatus;

}
