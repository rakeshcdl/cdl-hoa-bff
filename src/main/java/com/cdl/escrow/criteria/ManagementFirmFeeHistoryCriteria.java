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
public class ManagementFirmFeeHistoryCriteria implements Serializable {

    private LongFilter id;

    private DoubleFilter mffhAmount;

    private DoubleFilter mffhTotalAmount;

    private DoubleFilter mffhVatPercentage;

    private ZonedDateTimeFilter mffhTransactionDate;

    private BooleanFilter mffhSuccess;

    private BooleanFilter mffhStatus;

    private StringFilter mfhfRemark;

    private StringFilter mffhFeeResponse;

    private StringFilter mffhResponseStatus;

    private StringFilter mffhSpecialField1;

    private StringFilter mffhSpecialField2;

    private StringFilter mffhSpecialField3;

    private StringFilter mffhSpecialField4;

    private StringFilter mffhSpecialField5;

    private StringFilter mffhFeeRequestBody;

    private LongFilter managementFirmFeeId;

    private LongFilter managementFirmId;

    private LongFilter ownerRegistryUnitId;
}
