package com.cdl.escrow.criteria;

import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.DoubleFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BudgetItemCriteria implements Serializable {

    private LongFilter id;

    private StringFilter subCategoryCode;

    private StringFilter subCategoryName;

    private StringFilter subCategoryNameLocale;

    private StringFilter serviceCode;

    private StringFilter provisionalServiceCode;

    private StringFilter serviceName;

    private StringFilter serviceNameLocale;

    private DoubleFilter totalBudget;

    private DoubleFilter availableBudget;

    private DoubleFilter utilizedBudget;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;

    private LongFilter budgetCategoryId;

    private LongFilter budgetId;

}
