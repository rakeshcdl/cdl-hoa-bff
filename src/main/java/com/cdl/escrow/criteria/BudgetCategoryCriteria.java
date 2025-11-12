package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BudgetCategoryCriteria implements Serializable {

    private LongFilter id;

    private IntegerFilter serviceChargeGroupId;

    private StringFilter serviceChargeGroupName;

    private StringFilter serviceChargeGroupNameLocale;

    private StringFilter usageLocale;

    private StringFilter serviceName;

    private StringFilter serviceCode;

    private StringFilter provisionalBudgetCode;

    private IntegerFilter chargeTypeId;

    private StringFilter chargeType;

    private StringFilter usage;

    private ZonedDateTimeFilter budgetPeriodFrom;

    private ZonedDateTimeFilter budgetPeriodTo;

    private StringFilter budgetPeriodTitle;

    private StringFilter categoryCode;

    private StringFilter categoryName;

    private StringFilter categoryNameLocale;

    private StringFilter categorySubCode;

    private StringFilter categorySubName;

    private StringFilter categorySubToSubCode;

    private StringFilter categorySubToSubName;

    private DoubleFilter vatAmount;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;

    private LongFilter budgetId;

}
