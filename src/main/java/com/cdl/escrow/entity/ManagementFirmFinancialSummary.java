package com.cdl.escrow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Represents the financial summary of a Real Estate Asset,
 * including total funds deposited, disbursed, remaining balance, and budget overview.
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Jacksonized
@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "management_firm_financial_summary")
public class ManagementFirmFinancialSummary implements Serializable {
    @Id
    @SequenceGenerator(
            name = "management_firm_financial_summary_id_seq_gen",
            sequenceName = "management_firm_financial_summary_id_seq",
            allocationSize = 50
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "management_firm_financial_summary_id_seq_gen"
    )
    private Long id ;

    private String mffsEstRevenue ;

    private Double mffsEstConstructionCost;

    private Double mffsEstProjectMgmtExpense ;

    private Double mffsEstLandCost ;

    private Double mffsEstMarketingExpense ;

    private ZonedDateTime  mffsEstimatedDate;

    private String  mffsEstExceptionalCapVal ;

    private Double mffsActualSoldValue ;

    private Double mffsActualConstructionCost ;

    private Double mffsActualInfraCost ;

    private Double mffsActualLandCost ;

    private Double mffsActualMarketingExp ;

    private Double mffsActualProjectMgmtExpense ;

    private ZonedDateTime mffsActualDate ;

    private String  mffsActualexceptCapVal ;

    private Double mffsCurrentCashReceived ;

    private Double mffsCurCashRecvdOutEscrow ;

    private Double mffsCurCashRecvdWithinEscrow ;

    private Double mffsCurCashRecvdTotal ;

    private String mffsCurCashexceptCapVal ;

    private Double mffsCurrentLandCost ;

    private Double mffsCurLandCostOut ;

    private Double mffsCurLandCostWithin ;

    private Double mffsCurLandTotal ;

    private String mffsCurLandexceptCapVal ;

    private Double mffsCurrentConstructionCost ;

    private Double mffsCurConsCostWithin ;

    private Double mffsCurConsCostOut ;

    private Double mffsCurConsCostTotal ;

    private String mffsCurConsExcepCapVal ;

    private Double mffsCurrentMarketingExp ;

    private Double mffsCurrentMktgExpWithin ;

    private Double mffsCurrentMktgExpOut ;

    private Double mffsCurrentMktgExpTotal ;

    private String mffsCurrentmktgExcepCapVal ;

    private Double mffsCurrentProjectMgmtExp ;

    private Double mffsCurProjMgmtExpWithin ;

    private Double mffsCurProjMgmtExpOut ;

    private Double mffsCurProjMgmtExpTotal ;

    private String mffsCurProjExcepCapVal ;

    private Double mffsCurrentMortgage ;

    private Double mffsCurrentMortgageWithin ;

    private Double currentMortgageOut ;

    private Double mffsCurrentMortgageTotal ;

    private String mffsCurMortgageExceptCapVal ;

    private Double mffsCurrentVatPayment ;

    private Double mffsCurrentVatPaymentWithin ;

    private Double mffsCurrentVatPaymentOut ;

    private Double mffsCurrentVatPaymentTotal ;

    private String mffsCurVatExceptCapVal ;

    private Double mffsCurrentOqood ;

    private Double mffsCurrentOqoodWithin ;

    private Double mffsCurrentOqoodOut ;

    private Double mffsCurrentOqoodTotal ;

    private String mffsCurOqoodExceptCapVal ;

    private Double mffsCurrentRefund ;

    private Double mffsCurrentRefundWithin ;

    private Double mffsCurrentRefundOut ;

    private Double mffsCurrentRefundTotal ;

    private String mffsCurRefundExceptCapVal ;

    private Double mffsCurrentBalInRetenAcc ;

    private Double mffsCurBalInRetenAccWithin ;

    private Double mffsCurBalInRetenAccOut ;

    private Double mffsCurBalInRetenAccTotal ;

    private String mffsCurBalInRetenExceptCapVal ;

    private Double mffsCurrentBalInTrustAcc ;

    private Double mffsCurBalInTrustAccWithin ;

    private Double mffsCurBalInTrustAccOut ;

    private Double mffsCurBalInTrustAccTotal ;

    private String mffsCurBalInExceptCapVal ;

    private Double mffsCurrentTechnicalFee ;

    private Double mffsCurTechnFeeWithin ;

    private Double mffsCurTechnFeeOut ;

    private Double mffsCurTechnFeeTotal ;

    private String mffsCurTechFeeExceptCapVal ;

    private Double mffsCurrentUnIdentifiedFund ;

    private Double mffsCurUnIdeFundWithin ;

    private Double mffsCurUnIdeFundOut ;

    private Double mffsCurUnIdeFundTotal ;

    private String mffsCurUnIdeExceptCapVal ;

    private Double mffsCurrentLoanInstal ;

    private Double mffsCurLoanInstalWithin ;

    private Double mffsCurLoanInstalOut ;

    private Double mffsCurLoanInstalTotal ;

    private String mffsCurLoanExceptCapVal ;

    private Double mffsCurrentInfraCost ;

    private Double mffsCurInfraCostWithin ;

    private Double mffsCurInfraCostOut ;

    private Double mffsCurInfraCostTotal ;

    private String mffsCurInfraExceptCapVal ;

    private Double mffsCurrentOthersCost ;

    private Double mffsCurOthersCostWithin ;

    private Double mffsCurOthersCostOut ;

    private Double mffsCurOthersCostTotal ;

    private String mffsCurOthersExceptCapVal ;

    private Double mffsCurrentTransferredCost ;

    private Double mffsCurTransferCostWithin ;

    private Double mffsCurTransferCostOut ;

    private Double mffsCurTransferCostTotal ;

    private String mffsCurTransferExceptCapVal ;

    private Double mffsCurrentForfeitedCost ;

    private Double mffsCurForfeitCostWithin ;

    private Double mffsCurForfeitCostOut ;

    private Double mffsCurForfeitCostTotal ;

    private String mffsCurForfeitExceptCapVal ;

    private Double mffsCurrentDeveloperEquitycost ;

    private Double mffsCurDeveEqtycostWithin ;

    private Double mffsCurDeveEqtycostOut ;

    private Double mffsCurDeveEqtycostTotal ;

    private String mffsCurDeveExceptCapVal ;

    private Double mffsCurrentAmantFund ;

    private Double mffsCurAmntFundWithin ;

    private Double mffsCurAmntFundOut ;

    private Double mffsCurAmntFundTotal ;

    private String mffsCurAmntExceptCapVal ;

    private Double mffsCurrentOtherWithdrawls ;

    private Double mffsCurOtherWithdWithin ;

    private Double mffsCurOtherWithdOut ;

    private Double mffsCurOtherWithdTotal ;

    private String mffsCurOtherExceptCapVal ;

    private Double mffsCurrentOqoodOtherFeePay ;

    private Double mffsCurOqoodOthFeeWithin ;

    private Double mffsCurOqoodOthFeeOut ;

    private Double mffsCurOqoodOthFeeTotal ;

    private Double mffsCurrentVatDeposit ;

    private Double mffsCurVatDepositWithin ;

    private Double mffsCurVatDepositOut ;

    private Double mffsCurVatDepositTotal ;

    private String mffsCurVatDepositCapVal ;

    private Double mffsCurBalConstructionTotal ;

    private Double mffsCurBalConstructionWithin ;

    private Double mffsCurBalConstructionOut ;

    private String mffsCurBalExcepCapVal ;

    private Double mffsCreditInterest ;

    private Double mffsPaymentForRetentionAcc ;

    private Double mffsDeveloperReimburse ;

    private Double mffsUnitRegFees ;

    private Double mffsCreditInterestProfit ;

    private Double mffsVatCappedCost ;

    private String mffsExceptionalCapVal ;


    private Double mffsCurrentBalInSubsConsAcc ;

    private Double mffsCurBalInRSubsConsWithin ;

    private Double mffsCurBalInSubsConsOut ;

    private Double mffsCurBalInSubsConsTotal ;

    private String mffsCurBalInSubsConsCapVal ;

    private String mffsOtherFeesAnPaymentExcepVal;

    @ManyToOne
    @JsonIgnore
    private AssetRegister assetRegister;

    @ManyToOne
    @JsonIgnore
    private ManagementFirm managementFirm;
    private Boolean enabled ;

    private Boolean deleted;

    // New added

    private ZonedDateTime mffsInitiatedDate;

    private ApplicationSetting currentBalanceCategory;

}
