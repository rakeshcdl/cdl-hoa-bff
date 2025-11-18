package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetDTO implements Serializable {

    private Long id;

    private String budgetId;

    private String budgetName;

    private Boolean isActive;

    private String budgetPeriodCode;

    private Integer propertyGroupId;

    private String propertyManagerEmail;

    private String masterCommunityName;

    private String masterCommunityNameLocale;

    private String createdBy;

    private Boolean enabled ;

    private Boolean deleted;

    private AssetRegisterDTO assetRegisterDTO;

    private ManagementFirmDTO managementFirmDTO;

    //private Set<FundEgress> fundEgresses = new HashSet<>();

    private Set<BudgetCategoryDTO> budgetCategoriesDTOS = new HashSet<>();

    private TaskStatusDTO taskStatusDTO;
}
