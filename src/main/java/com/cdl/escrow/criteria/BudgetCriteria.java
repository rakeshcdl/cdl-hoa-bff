package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.IntegerFilter;
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
public class BudgetCriteria implements Serializable {

    private LongFilter id;

    private StringFilter budgetId;

    private BooleanFilter isActive;

    private StringFilter budgetPeriodCode;

    private IntegerFilter propertyGroupId;

    private StringFilter propertyManagerEmail;

    private StringFilter masterCommunityName;

    private StringFilter masterCommunityNameLocale;

    private StringFilter createdBy;

    private LongFilter buildPartnerId;

    private LongFilter realEstateAssestId;


}
