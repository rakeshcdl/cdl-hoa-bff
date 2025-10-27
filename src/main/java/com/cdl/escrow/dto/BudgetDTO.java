package com.cdl.escrow.dto;

import com.cdl.escrow.entity.BudgetCategory;
import com.cdl.escrow.entity.BuildPartner;
import com.cdl.escrow.entity.FundEgress;
import com.cdl.escrow.entity.RealEstateAssest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    private Boolean isActive;

    private String budgetPeriodCode;

    private Integer propertyGroupId;

    private String propertyManagerEmail;

    private String masterCommunityName;

    private String masterCommunityNameLocale;

    private String createdBy;

    private BuildPartnerDTO buildPartnerDTO;

    private RealEstateAssestDTO realEstateAssestDTO;

    //private Set<FundEgress> fundEgresses = new HashSet<>();

    private Set<BudgetCategoryDTO> budgetCategoriesDTOS = new HashSet<>();
}
