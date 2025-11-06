package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.DoubleFilter;
import com.cdl.escrow.filter.LongFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ManagementFirmClosureCriteria implements Serializable {

    private LongFilter id;

    private DoubleFilter reacTotalIncomeFund;

    private DoubleFilter reacTotalPayment;

    private DoubleFilter reacCheckGuranteeDoc;

    private LongFilter realEstateAssestId;

    private LongFilter reacDocumentId;
}
