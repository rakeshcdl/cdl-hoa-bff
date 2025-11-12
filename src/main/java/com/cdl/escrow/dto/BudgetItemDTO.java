package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetItemDTO implements Serializable {

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

    private BudgetCategoryDTO budgetCategoryDTO;

   // private Set<FundEgress> fundEgresses = new HashSet<>();
}
