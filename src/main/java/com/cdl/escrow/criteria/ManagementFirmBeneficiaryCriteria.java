package com.cdl.escrow.criteria;


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
public class ManagementFirmBeneficiaryCriteria implements Serializable {

    private LongFilter id;

    private StringFilter mfbBeneficiaryId;

    private StringFilter mfbName;

    private DoubleFilter mfbContractAmount;

    private DoubleFilter mfbActualLandPrice;

    private StringFilter mfbContractorName;

    private StringFilter mfbType;

    private StringFilter mfbBank;

    private StringFilter mfbSwift;

    private StringFilter mfbRoutingCode;

    private StringFilter mfbAddress;

    private StringFilter mfbBankAddress;

    private BooleanFilter mfbIsActive;

    private BooleanFilter mfbIsDeleted;

    private LongFilter mfbTransferTypeId;

    private LongFilter mfbExpenseTypeId;

    private LongFilter mfbVendorSubTypeId;

    private LongFilter mfbContractorSubTypeId;

    private LongFilter mfbInfrastructureCategoryId;

    private LongFilter mfbSalesCategoryId;

   private LongFilter managementFirmsId ;

   // private Set<BankAccount> bankAccountDTOS ;

   // private Set<FundEgress> fundEgressDTOS;
}
