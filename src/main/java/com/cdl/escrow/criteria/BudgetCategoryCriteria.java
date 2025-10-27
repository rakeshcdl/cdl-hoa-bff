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

    private StringFilter usage;

    private ZonedDateTimeFilter budgetPeriodFrom;

    private ZonedDateTimeFilter budgetPeriodTo;

    private StringFilter budgetPeriodTitle;

    private StringFilter categoryCode;

    private StringFilter categoryName;

    private StringFilter categoryNameLocale;

    private DoubleFilter vatAmount;

    private LongFilter budgetId;

}
