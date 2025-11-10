package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.DoubleFilter;
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
public class ManagementFirmFeeCriteria implements Serializable {

    private LongFilter id;

    private DoubleFilter mffAmount;

    private DoubleFilter mffTotalAmount;

    private ZonedDateTimeFilter mffCalender;

    private ZonedDateTimeFilter mffNextRecoveryDate;

    private DoubleFilter mffVatPercentage;

    private BooleanFilter mffCollected;

    private LongFilter managementFirmId;

    private LongFilter mffCategoryId;

    private LongFilter mffCurrencyId;

    private LongFilter mffFrequencyId;

    //private Set<ManagementFirmHistoryDTO> mflEstateAssestFeeHistoryDTOS ;
}
