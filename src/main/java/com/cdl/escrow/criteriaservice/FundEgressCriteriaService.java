package com.cdl.escrow.criteriaservice;

import com.cdl.escrow.criteria.FundEgressCriteria;
import com.cdl.escrow.dto.FundEgressDTO;
import com.cdl.escrow.entity.*;
import com.cdl.escrow.filter.BaseSpecificationBuilder;
import com.cdl.escrow.mapper.FundEgressMapper;
import com.cdl.escrow.repository.FundEgressRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FundEgressCriteriaService extends BaseSpecificationBuilder<FundEgress> implements Serializable {

    private final transient FundEgressRepository fundEgressRepository;

    private final transient FundEgressMapper fundEgressMapper;

    public Page<FundEgressDTO> findByCriteria(FundEgressCriteria criteria, Pageable pageable) {
        Specification<FundEgress> specification = createSpecification(criteria);
        return fundEgressRepository.findAll(specification, pageable).map(fundEgressMapper::toDto);
    }

    private Specification<FundEgress> createSpecification(FundEgressCriteria criteria) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

           if(criteria!=null)
           {
               addLongFilter(cb, root, predicates, "id", criteria.getId());

               addStringFilter(cb, root, predicates, "feInvoiceNumber", criteria.getFeInvoiceNumber(), true);
               addZonedDateTimeFilter(cb, root, predicates, "fePaymentDate", criteria.getFePaymentDate());
               addDoubleFilter(cb, root, predicates, "fePaymentAmount", criteria.getFePaymentAmount());
               addStringFilter(cb, root, predicates, "feGlAccountNumber", criteria.getFeGlAccountNumber(), true);
               addStringFilter(cb, root, predicates, "feGlBranchCode", criteria.getFeGlBranchCode(), true);
               addStringFilter(cb, root, predicates, "feUnitRegistrationFee", criteria.getFeUnitRegistrationFee(), true);
               addStringFilter(cb, root, predicates, "feRemark", criteria.getFeRemark(), true);
               addStringFilter(cb, root, predicates, "fePaymentRefNumber", criteria.getFePaymentRefNumber(), true);
               addBooleanFilter(cb, root, predicates, "feSplitPayment", criteria.getFeSplitPayment());
               addZonedDateTimeFilter(cb, root, predicates, "feInvoiceDate", criteria.getFeInvoiceDate());
               addStringFilter(cb, root, predicates, "feRtZeroThree", criteria.getFeRtZeroThree(), true);
               addStringFilter(cb, root, predicates, "feEngineerRefNo", criteria.getFeEngineerRefNo(), true);
               addZonedDateTimeFilter(cb, root, predicates, "feEngineerApprovalDate", criteria.getFeEngineerApprovalDate());
               addStringFilter(cb, root, predicates, "feReraApprovedRefNo", criteria.getFeReraApprovedRefNo(), true);
               addZonedDateTimeFilter(cb, root, predicates, "feReraApprovedDate", criteria.getFeReraApprovedDate());
               addStringFilter(cb, root, predicates, "feInvoiceRefNo", criteria.getFeInvoiceRefNo(), true);
               addDoubleFilter(cb, root, predicates, "feEngineerApprovedAmt", criteria.getFeEngineerApprovedAmt());
               addDoubleFilter(cb, root, predicates, "feTotalEligibleAmtInv", criteria.getFeTotalEligibleAmtInv());
               addDoubleFilter(cb, root, predicates, "feAmtPaidAgainstInv", criteria.getFeAmtPaidAgainstInv());
               addStringFilter(cb, root, predicates, "feCapExceeded", criteria.getFeCapExceeded(), true);
               addDoubleFilter(cb, root, predicates, "feTotalAmountPaid", criteria.getFeTotalAmountPaid());
               addDoubleFilter(cb, root, predicates, "feDebitFromEscrow", criteria.getFeDebitFromEscrow());
               addDoubleFilter(cb, root, predicates, "feCurEligibleAmt", criteria.getFeCurEligibleAmt());
               addDoubleFilter(cb, root, predicates, "feDebitFromRetention", criteria.getFeDebitFromRetention());
               addDoubleFilter(cb, root, predicates, "feTotalPayoutAmt", criteria.getFeTotalPayoutAmt());
               addDoubleFilter(cb, root, predicates, "feAmountInTransit", criteria.getFeAmountInTransit());
               addStringFilter(cb, root, predicates, "feVarCapExceeded", criteria.getFeVarCapExceeded(), true);
               addDoubleFilter(cb, root, predicates, "feIndicativeRate", criteria.getFeIndicativeRate());
               addStringFilter(cb, root, predicates, "fePpcNumber", criteria.getFePpcNumber(), true);
               addBooleanFilter(cb, root, predicates, "feCorporatePayment", criteria.getFeCorporatePayment());
               addStringFilter(cb, root, predicates, "feNarration1", criteria.getFeNarration1(), true);
               addStringFilter(cb, root, predicates, "feNarration2", criteria.getFeNarration2(), true);
               addDoubleFilter(cb, root, predicates, "feAmtRecdFromUnitHolder", criteria.getFeAmtRecdFromUnitHolder());
               addBooleanFilter(cb, root, predicates, "feForFeit", criteria.getFeForFeit());
               addDoubleFilter(cb, root, predicates, "feForFeitAmt", criteria.getFeForFeitAmt());
               addBooleanFilter(cb, root, predicates, "feRefundToUnitHolder", criteria.getFeRefundToUnitHolder());
               addDoubleFilter(cb, root, predicates, "feRefundAmount", criteria.getFeRefundAmount());
               addBooleanFilter(cb, root, predicates, "feTransferToOtherUnit", criteria.getFeTransferToOtherUnit());
               addDoubleFilter(cb, root, predicates, "feTransferAmount", criteria.getFeTransferAmount());
               addStringFilter(cb, root, predicates, "feUnitReraApprovedRefNo", criteria.getFeUnitReraApprovedRefNo(), true);
               addZonedDateTimeFilter(cb, root, predicates, "feUnitTransferAppDate", criteria.getFeUnitTransferAppDate());
               addBooleanFilter(cb, root, predicates, "feBeneficiaryToMaster", criteria.getFeBeneficiaryToMaster());
               addDoubleFilter(cb, root, predicates, "feAmountToBeReleased", criteria.getFeAmountToBeReleased());
               addZonedDateTimeFilter(cb, root, predicates, "feBeneDateOfPayment", criteria.getFeBeneDateOfPayment());
               addDoubleFilter(cb, root, predicates, "feBeneVatPaymentAmt", criteria.getFeBeneVatPaymentAmt());
               addBooleanFilter(cb, root, predicates, "feIncludeInPayout", criteria.getFeIncludeInPayout());
               addDoubleFilter(cb, root, predicates, "feBankCharges", criteria.getFeBankCharges());
               addBooleanFilter(cb, root, predicates, "feTasPaymentSuccess", criteria.getFeTasPaymentSuccess());
               addBooleanFilter(cb, root, predicates, "feTasPaymentReturn", criteria.getFeTasPaymentReturn());
               addBooleanFilter(cb, root, predicates, "feDiscardPayment", criteria.getFeDiscardPayment());
               addBooleanFilter(cb, root, predicates, "feIsTasPayment", criteria.getFeIsTasPayment());
               addBooleanFilter(cb, root, predicates, "feIsManualPayment", criteria.getFeIsManualPayment());
               addStringFilter(cb, root, predicates, "feSpecialField1", criteria.getFeSpecialField1(), true);
               addStringFilter(cb, root, predicates, "feSpecialField2", criteria.getFeSpecialField2(), true);
               addStringFilter(cb, root, predicates, "feSpecialField3", criteria.getFeSpecialField3(), true);
               addStringFilter(cb, root, predicates, "feUniqueRefNo", criteria.getFeUniqueRefNo(), true);
               addStringFilter(cb, root, predicates, "fePaymentResponseObj", criteria.getFePaymentResponseObj(), true);
               addStringFilter(cb, root, predicates, "fePaymentStatus", criteria.getFePaymentStatus(), true);
               addStringFilter(cb, root, predicates, "feResPaymentRefNo", criteria.getFeResPaymentRefNo(), true);
               addStringFilter(cb, root, predicates, "feResUniqueRefNo", criteria.getFeResUniqueRefNo(), true);
               addStringFilter(cb, root, predicates, "feResHeader", criteria.getFeResHeader(), true);
               addDoubleFilter(cb, root, predicates, "feInvoiceValue", criteria.getFeInvoiceValue());
               addStringFilter(cb, root, predicates, "feSpecialField4", criteria.getFeSpecialField4(), true);
               addStringFilter(cb, root, predicates, "feSpecialField5", criteria.getFeSpecialField5(), true);
               addStringFilter(cb, root, predicates, "feSpecialField6", criteria.getFeSpecialField6(), true);
               addStringFilter(cb, root, predicates, "feTasPaymentStatus", criteria.getFeTasPaymentStatus(), true);
               addStringFilter(cb, root, predicates, "feCorpCertEngFee", criteria.getFeCorpCertEngFee(), true);
               addStringFilter(cb, root, predicates, "feSpecialField7", criteria.getFeSpecialField7(), true);
               addDoubleFilter(cb, root, predicates, "feCurBalInEscrowAcc", criteria.getFeCurBalInEscrowAcc());
               addDoubleFilter(cb, root, predicates, "feCurBalInRetentionAcc", criteria.getFeCurBalInRetentionAcc());
               addStringFilter(cb, root, predicates, "feEngineerFeePayment", criteria.getFeEngineerFeePayment(), true);
               addDoubleFilter(cb, root, predicates, "feCorporateAccBalance", criteria.getFeCorporateAccBalance());
               addDoubleFilter(cb, root, predicates, "feSubConsAccBalance", criteria.getFeSubConsAccBalance());
               addStringFilter(cb, root, predicates, "feErrorResponseObject", criteria.getFeErrorResponseObject(), true);
               addStringFilter(cb, root, predicates, "fePropertyRegistrationFee", criteria.getFePropertyRegistrationFee(), true);
               addDoubleFilter(cb, root, predicates, "feBalanceAmount", criteria.getFeBalanceAmount());
               addBooleanFilter(cb, root, predicates, "feDocVerified", criteria.getFeDocVerified());
               addStringFilter(cb, root, predicates, "fePaymentBodyObj", criteria.getFePaymentBodyObj(), true);
               addStringFilter(cb, root, predicates, "feDealRefNo", criteria.getFeDealRefNo(), true);
               addBooleanFilter(cb, root, predicates, "feSpecialRate", criteria.getFeSpecialRate());
               addDoubleFilter(cb, root, predicates, "feTreasuryRate", criteria.getFeTreasuryRate());
               addBooleanFilter(cb, root, predicates, "feBenefFromProject", criteria.getFeBenefFromProject());
               addDoubleFilter(cb, root, predicates, "feCorporatePaymentEngFee", criteria.getFeCorporatePaymentEngFee());
               addBooleanFilter(cb, root, predicates, "feIsEngineerFee", criteria.getFeIsEngineerFee());


               if (criteria.getBeneficiaryFeePaymentId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("beneficiaryFeePayment", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBeneficiaryFeePaymentId());
               }

               if (criteria.getPayoutToBeMadeFromCbsId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("payoutToBeMadeFromCbs", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPayoutToBeMadeFromCbsId());
               }

               if (criteria.getChargedCodeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("chargedCode", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getChargedCodeId());
               }

               if (criteria.getPaymentModeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("paymentMode", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentModeId());
               }

               if (criteria.getTransactionTypeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("transactionType", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getTransactionTypeId());
               }

               if (criteria.getPaymentStatusOptionId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("paymentStatusOption", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentStatusOptionId());
               }

               if (criteria.getVoucherPaymentTypeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("voucherPaymentType", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getVoucherPaymentTypeId());
               }

               if (criteria.getVoucherPaymentSubTypeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("voucherPaymentSubType", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getVoucherPaymentSubTypeId());
               }

               if (criteria.getExpenseTypeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("expenseType", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getExpenseTypeId());
               }

               if (criteria.getExpenseSubTypeId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("expenseSubType", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getExpenseSubTypeId());
               }

               if (criteria.getInvoiceCurrencyId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("invoiceCurrency", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getInvoiceCurrencyId());
               }

               if (criteria.getPaymentCurrencyId() != null) {
                   Join<FundEgress, ApplicationSetting> join = root.join("paymentCurrency", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getPaymentCurrencyId());
               }


               if (criteria.getManagementFirmId() != null) {
                   Join<FundEgress, ManagementFirm> join = root.join("managementFirm", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getManagementFirmId());
               }

               if (criteria.getOwnerRegistryUnitId() != null) {
                   Join<FundEgress, OwnerRegistryUnit> join = root.join("ownerRegistryUnit", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getOwnerRegistryUnitId());
               }

               if (criteria.getTransferOwnerRegistryUnitId() != null) {
                   Join<FundEgress, OwnerRegistryUnit> join = root.join("transferOwnerRegistryUnit", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getTransferOwnerRegistryUnitId());
               }

               if (criteria.getAssetRegisterId() != null) {
                   Join<FundEgress, AssetRegister> join = root.join("assetRegister", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getAssetRegisterId());
               }

               if (criteria.getManagementFirmBeneficiaryId() != null) {
                   Join<FundEgress, ManagementFirmBeneficiary> join = root.join("managementFirmBeneficiary", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getManagementFirmBeneficiaryId());
               }

               if (criteria.getTaskStatusId() != null) {
                   Join<FundEgress, TaskStatus> join = root.join("taskStatus", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getTaskStatusId());
               }

               if (criteria.getBudgetId() != null) {
                   Join<FundEgress, TaskStatus> join = root.join("budget", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBudgetId());
               }

               if (criteria.getBudgetCategoryId() != null) {
                   Join<FundEgress, TaskStatus> join = root.join("budgetCategory", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBudgetCategoryId());
               }

               if (criteria.getBudgetItemId() != null) {
                   Join<FundEgress, TaskStatus> join = root.join("budgetItem", JoinType.LEFT);
                   addLongFilterOnJoin(cb, join, predicates, "id", criteria.getBudgetItemId());
               }

               addBooleanFilter(cb, root, predicates, "deleted", criteria.getDeleted());
               addBooleanFilter(cb, root, predicates, "enabled", criteria.getEnabled());

           }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
