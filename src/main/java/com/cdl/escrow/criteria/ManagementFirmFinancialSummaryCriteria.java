package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.DoubleFilter;
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
public class ManagementFirmFinancialSummaryCriteria implements Serializable {

    private LongFilter id ;

    private StringFilter mffsEstRevenue ;

    private DoubleFilter mffsEstConstructionCost;

    private DoubleFilter mffsEstProjectMgmtExpense ;

    private DoubleFilter mffsEstLandCost ;

    private DoubleFilter mffsEstMarketingExpense ;

    private ZonedDateTimeFilter mffsEstimatedDate;

    private StringFilter  mffsEstExceptionalCapVal ;

    private DoubleFilter mffsActualSoldValue ;

    private DoubleFilter mffsActualConstructionCost ;

    private DoubleFilter mffsActualInfraCost ;

    private DoubleFilter mffsActualLandCost ;

    private DoubleFilter mffsActualMarketingExp ;

    private DoubleFilter mffsActualProjectMgmtExpense ;

    private ZonedDateTimeFilter mffsActualDate ;

    private StringFilter  mffsActualexceptCapVal ;

    private DoubleFilter mffsCurrentCashReceived ;

    private DoubleFilter mffsCurCashRecvdOutEscrow ;

    private DoubleFilter mffsCurCashRecvdWithinEscrow ;

    private DoubleFilter mffsCurCashRecvdTotal ;

    private StringFilter mffsCurCashexceptCapVal ;

    private DoubleFilter mffsCurrentLandCost ;

    private DoubleFilter mffsCurLandCostOut ;

    private DoubleFilter mffsCurLandCostWithin ;

    private DoubleFilter mffsCurLandTotal ;

    private StringFilter mffsCurLandexceptCapVal ;

    private DoubleFilter mffsCurrentConstructionCost ;

    private DoubleFilter mffsCurConsCostWithin ;

    private DoubleFilter mffsCurConsCostOut ;

    private DoubleFilter mffsCurConsCostTotal ;

    private StringFilter mffsCurConsExcepCapVal ;

    private DoubleFilter mffsCurrentMarketingExp ;

    private DoubleFilter mffsCurrentMktgExpWithin ;

    private DoubleFilter mffsCurrentMktgExpOut ;

    private DoubleFilter mffsCurrentMktgExpTotal ;

    private StringFilter mffsCurrentmktgExcepCapVal ;

    private DoubleFilter mffsCurrentProjectMgmtExp ;

    private DoubleFilter mffsCurProjMgmtExpWithin ;

    private DoubleFilter mffsCurProjMgmtExpOut ;

    private DoubleFilter mffsCurProjMgmtExpTotal ;

    private StringFilter mffsCurProjExcepCapVal ;

    private DoubleFilter mffsCurrentMortgage ;

    private DoubleFilter mffsCurrentMortgageWithin ;

    private DoubleFilter currentMortgageOut ;

    private DoubleFilter mffsCurrentMortgageTotal ;

    private StringFilter mffsCurMortgageExceptCapVal ;

    private DoubleFilter mffsCurrentVatPayment ;

    private DoubleFilter mffsCurrentVatPaymentWithin ;

    private DoubleFilter mffsCurrentVatPaymentOut ;

    private DoubleFilter mffsCurrentVatPaymentTotal ;

    private StringFilter mffsCurVatExceptCapVal ;

    private DoubleFilter mffsCurrentOqood ;

    private DoubleFilter mffsCurrentOqoodWithin ;

    private DoubleFilter mffsCurrentOqoodOut ;

    private DoubleFilter mffsCurrentOqoodTotal ;

    private StringFilter mffsCurOqoodExceptCapVal ;

    private DoubleFilter mffsCurrentRefund ;

    private DoubleFilter mffsCurrentRefundWithin ;

    private DoubleFilter mffsCurrentRefundOut ;

    private DoubleFilter mffsCurrentRefundTotal ;

    private StringFilter mffsCurRefundExceptCapVal ;

    private DoubleFilter mffsCurrentBalInRetenAcc ;

    private DoubleFilter mffsCurBalInRetenAccWithin ;

    private DoubleFilter mffsCurBalInRetenAccOut ;

    private DoubleFilter mffsCurBalInRetenAccTotal ;

    private StringFilter mffsCurBalInRetenExceptCapVal ;

    private DoubleFilter mffsCurrentBalInTrustAcc ;

    private DoubleFilter mffsCurBalInTrustAccWithin ;

    private DoubleFilter mffsCurBalInTrustAccOut ;

    private DoubleFilter mffsCurBalInTrustAccTotal ;

    private StringFilter mffsCurBalInExceptCapVal ;

    private DoubleFilter mffsCurrentTechnicalFee ;

    private DoubleFilter mffsCurTechnFeeWithin ;

    private DoubleFilter mffsCurTechnFeeOut ;

    private DoubleFilter mffsCurTechnFeeTotal ;

    private StringFilter mffsCurTechFeeExceptCapVal ;

    private DoubleFilter mffsCurrentUnIdentifiedFund ;

    private DoubleFilter mffsCurUnIdeFundWithin ;

    private DoubleFilter mffsCurUnIdeFundOut ;

    private DoubleFilter mffsCurUnIdeFundTotal ;

    private StringFilter mffsCurUnIdeExceptCapVal ;

    private DoubleFilter mffsCurrentLoanInstal ;

    private DoubleFilter mffsCurLoanInstalWithin ;

    private DoubleFilter mffsCurLoanInstalOut ;

    private DoubleFilter mffsCurLoanInstalTotal ;

    private StringFilter mffsCurLoanExceptCapVal ;

    private DoubleFilter mffsCurrentInfraCost ;

    private DoubleFilter mffsCurInfraCostWithin ;

    private DoubleFilter mffsCurInfraCostOut ;

    private DoubleFilter mffsCurInfraCostTotal ;

    private StringFilter mffsCurInfraExceptCapVal ;

    private DoubleFilter mffsCurrentOthersCost ;

    private DoubleFilter mffsCurOthersCostWithin ;

    private DoubleFilter mffsCurOthersCostOut ;

    private DoubleFilter mffsCurOthersCostTotal ;

    private StringFilter mffsCurOthersExceptCapVal ;

    private DoubleFilter mffsCurrentTransferredCost ;

    private DoubleFilter mffsCurTransferCostWithin ;

    private DoubleFilter mffsCurTransferCostOut ;

    private DoubleFilter mffsCurTransferCostTotal ;

    private StringFilter mffsCurTransferExceptCapVal ;

    private DoubleFilter mffsCurrentForfeitedCost ;

    private DoubleFilter mffsCurForfeitCostWithin ;

    private DoubleFilter mffsCurForfeitCostOut ;

    private DoubleFilter mffsCurForfeitCostTotal ;

    private StringFilter mffsCurForfeitExceptCapVal ;

    private DoubleFilter mffsCurrentDeveloperEquitycost ;

    private DoubleFilter mffsCurDeveEqtycostWithin ;

    private DoubleFilter mffsCurDeveEqtycostOut ;

    private DoubleFilter mffsCurDeveEqtycostTotal ;

    private StringFilter mffsCurDeveExceptCapVal ;

    private DoubleFilter mffsCurrentAmantFund ;

    private DoubleFilter mffsCurAmntFundWithin ;

    private DoubleFilter mffsCurAmntFundOut ;

    private DoubleFilter mffsCurAmntFundTotal ;

    private StringFilter mffsCurAmntExceptCapVal ;

    private DoubleFilter mffsCurrentOtherWithdrawls ;

    private DoubleFilter mffsCurOtherWithdWithin ;

    private DoubleFilter mffsCurOtherWithdOut ;

    private DoubleFilter mffsCurOtherWithdTotal ;

    private StringFilter mffsCurOtherExceptCapVal ;

    private DoubleFilter mffsCurrentOqoodOtherFeePay ;

    private DoubleFilter mffsCurOqoodOthFeeWithin ;

    private DoubleFilter mffsCurOqoodOthFeeOut ;

    private DoubleFilter mffsCurOqoodOthFeeTotal ;

    private DoubleFilter mffsCurrentVatDeposit ;

    private DoubleFilter mffsCurVatDepositWithin ;

    private DoubleFilter mffsCurVatDepositOut ;

    private DoubleFilter mffsCurVatDepositTotal ;

    private StringFilter mffsCurVatDepositCapVal ;

    private DoubleFilter mffsCurBalConstructionTotal ;

    private DoubleFilter mffsCurBalConstructionWithin ;

    private DoubleFilter mffsCurBalConstructionOut ;

    private StringFilter mffsCurBalExcepCapVal ;

    private DoubleFilter mffsCreditInterest ;

    private DoubleFilter mffsPaymentForRetentionAcc ;

    private DoubleFilter mffsDeveloperReimburse ;

    private DoubleFilter mffsUnitRegFees ;

    private DoubleFilter mffsCreditInterestProfit ;

    private DoubleFilter mffsVatCappedCost ;

    private StringFilter mffsExceptionalCapVal ;

    private LongFilter assetRegisterId;

    private LongFilter managementFirmId;
}
