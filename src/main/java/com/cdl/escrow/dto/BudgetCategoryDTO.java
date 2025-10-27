package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetCategoryDTO implements Serializable {

    private Long id;

    private Integer serviceChargeGroupId;

    private String serviceChargeGroupName;

    private String serviceChargeGroupNameLocale;

    private String usageLocale;

    private String usage;

    private ZonedDateTime budgetPeriodFrom;

    private ZonedDateTime budgetPeriodTo;

    private String budgetPeriodTitle;

    private String categoryCode;

    private String categoryName;

    private String categoryNameLocale;

    private Double vatAmount;

    private BudgetDTO budgetDTO;

    //private Set<FundEgress> fundEgresses = new HashSet<>();

    private Set<BudgetItemDTO> budgetItemDTOS = new HashSet<>();
}
