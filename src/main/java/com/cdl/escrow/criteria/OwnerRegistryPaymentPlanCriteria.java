package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.DoubleFilter;
import com.cdl.escrow.filter.IntegerFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OwnerRegistryPaymentPlanCriteria implements Serializable {

    private LongFilter id;

    private IntegerFilter ownppInstallmentNumber;

    private ZonedDateTimeFilter ownppInstallmentDate;

    private DoubleFilter ownppBookingAmount;

    private LongFilter capitalPartnerId;
}
