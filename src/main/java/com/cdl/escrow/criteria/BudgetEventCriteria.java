package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BudgetEventCriteria implements Serializable {

    private LongFilter id;

    private StringFilter eventId;

    private StringFilter urc;

    private ZonedDateTimeFilter timeStamp;

    private StringFilter syncType;

    private StringFilter propertyGroupId;

    private StringFilter periodCode;

    private StringFilter managementCompanyId;

    private LongFilter acknowledgeRef;
}
