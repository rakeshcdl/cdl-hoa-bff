package com.cdl.escrow.criteria;

import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.entity.BudgetCategory;
import com.cdl.escrow.entity.BudgetItem;
import com.cdl.escrow.entity.TaskStatus;
import com.cdl.escrow.filter.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FundEgressCriteria implements Serializable {

    private LongFilter id ;

    private StringFilter feInvoiceNumber;

    private ZonedDateTimeFilter fePaymentDate;

    private DoubleFilter fePaymentAmount;

    private StringFilter feGlAccountNumber;

    private StringFilter feGlBranchCode;

    private StringFilter feUnitRegistrationFee;

    private StringFilter feRemark;

    private String feResponseObject;

    private StringFilter fePaymentRefNumber;

    private BooleanFilter feSplitPayment;

    private ZonedDateTimeFilter feInvoiceDate;

    private StringFilter feRtZeroThree;

    private StringFilter feEngineerRefNo;

    private ZonedDateTimeFilter feEngineerApprovalDate;

    private StringFilter feReraApprovedRefNo;

    private ZonedDateTimeFilter feReraApprovedDate;

    private StringFilter feInvoiceRefNo;

    private DoubleFilter feEngineerApprovedAmt;

    private DoubleFilter feTotalEligibleAmtInv;

    private DoubleFilter feAmtPaidAgainstInv;

    private StringFilter feCapExceeded;

    private DoubleFilter feTotalAmountPaid;

    private DoubleFilter feDebitFromEscrow;

    private DoubleFilter feCurEligibleAmt;

    private DoubleFilter feDebitFromRetention;

    private DoubleFilter feTotalPayoutAmt;

    private DoubleFilter feAmountInTransit;

    private StringFilter feVarCapExceeded;

    private DoubleFilter feIndicativeRate;

    private StringFilter fePpcNumber;

    private BooleanFilter feCorporatePayment;

    private StringFilter feNarration1;

    private StringFilter feNarration2;

    private DoubleFilter feAmtRecdFromUnitHolder;

    private BooleanFilter feForFeit;

    private DoubleFilter feForFeitAmt;

    private BooleanFilter feRefundToUnitHolder;

    private DoubleFilter feRefundAmount;

    private BooleanFilter feTransferToOtherUnit;

    private DoubleFilter feTransferAmount;

    private StringFilter feUnitReraApprovedRefNo;

    private ZonedDateTimeFilter feUnitTransferAppDate;

    private BooleanFilter feBeneficiaryToMaster;

    private DoubleFilter feAmountToBeReleased;

    private ZonedDateTimeFilter feBeneDateOfPayment;

    private DoubleFilter feBeneVatPaymentAmt;

    private BooleanFilter feIncludeInPayout;

    private DoubleFilter feBankCharges;

    private BooleanFilter feTasPaymentSuccess;

    private BooleanFilter feTasPaymentReturn;

    private BooleanFilter feDiscardPayment;

    private BooleanFilter feIsTasPayment;

    private BooleanFilter feIsManualPayment;

    private StringFilter feSpecialField1;

    private StringFilter feSpecialField2;

    private StringFilter feSpecialField3;

    private StringFilter feUniqueRefNo;

    private StringFilter fePaymentResponseObj;

    private StringFilter fePaymentStatus;

    private StringFilter feResPaymentRefNo;

    private StringFilter feResUniqueRefNo;

    private StringFilter feResHeader;

    private DoubleFilter feInvoiceValue;

    private StringFilter feSpecialField4;

    private StringFilter feSpecialField5;

    private StringFilter feSpecialField6;

    private StringFilter feTasPaymentStatus;

    private StringFilter feCorpCertEngFee;

    private StringFilter feSpecialField7;

    private DoubleFilter feCurBalInEscrowAcc;

    private DoubleFilter feCurBalInRetentionAcc;

    private StringFilter feEngineerFeePayment;

    private DoubleFilter feCorporateAccBalance;

    private DoubleFilter feSubConsAccBalance;

    private StringFilter feErrorResponseObject;

    private StringFilter fePropertyRegistrationFee;

    private DoubleFilter feBalanceAmount;

    private BooleanFilter feDocVerified;

    private StringFilter fePaymentBodyObj;

    private StringFilter feDealRefNo;

    private BooleanFilter feSpecialRate;

    private DoubleFilter feTreasuryRate;

    private BooleanFilter feBenefFromProject;

    private DoubleFilter feCorporatePaymentEngFee;

    private BooleanFilter feIsEngineerFee;

    // Mapping start
    private LongFilter paymentStatusOptionId;

    private LongFilter voucherPaymentTypeId;

    private LongFilter voucherPaymentSubTypeId;

    private LongFilter expenseTypeId;

    private LongFilter expenseSubTypeId;

    private LongFilter invoiceCurrencyId;

    private LongFilter paymentCurrencyId;

    private LongFilter chargedCodeId;

    private LongFilter paymentModeId;

    private LongFilter transactionTypeId;

    private LongFilter beneficiaryFeePaymentId;

    private LongFilter payoutToBeMadeFromCbsId;

    private LongFilter managementFirmId;

    private LongFilter ownerRegistryUnitId;

    private LongFilter transferOwnerRegistryUnitId;

    private LongFilter assetRegisterId;

    private LongFilter managementFirmBeneficiaryId;

    //private Set<ManagementFirmHistoryDTO> realEstateAssestFeeHistoryDTOS;

    //private LongFilter suretyBondId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;

    private LongFilter taskStatusId;

    // Budget

    private LongFilter budgetId;

    private LongFilter budgetCategoryId;

    private LongFilter budgetItemId;
}
