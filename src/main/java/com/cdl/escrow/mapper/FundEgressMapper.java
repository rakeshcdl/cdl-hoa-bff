package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.FundEgressDTO;
import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.entity.FundEgress;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FundEgressMapper  extends EntityMapper<FundEgressDTO, FundEgress> {

    @Mapping(source = "paymentStatusOption", target = "paymentStatusOptionDTO")
    @Mapping(source = "voucherPaymentType", target = "voucherPaymentTypeDTO")
    @Mapping(source = "voucherPaymentSubType", target = "voucherPaymentSubTypeDTO")
    @Mapping(source = "expenseType", target = "expenseTypeDTO")
    @Mapping(source = "expenseSubType", target = "expenseSubTypeDTO")
    @Mapping(source = "invoiceCurrency", target = "invoiceCurrencyDTO")
    @Mapping(source = "paymentCurrency", target = "paymentCurrencyDTO")
    @Mapping(source = "chargedCode", target = "chargedCodeDTO")
    @Mapping(source = "paymentMode", target = "paymentModeDTO")
    @Mapping(source = "transactionType", target = "transactionTypeDTO")
    @Mapping(source = "beneficiaryFeePayment", target = "beneficiaryFeePaymentDTO")
    @Mapping(source = "payoutToBeMadeFromCbs", target = "payoutToBeMadeFromCbsDTO")
    @Mapping(source = "managementFirm", target = "managementFirmDTO" )
    @Mapping(source = "ownerRegistryUnit", target = "ownerRegistryUnitDTO" )
    @Mapping(source = "transferOwnerRegistryUnit", target = "transferOwnerRegistryUnitDTO")
    @Mapping(source = "assetRegister", target = "assetRegisterDTO" )
    @Mapping(source = "managementFirmBeneficiary", target = "managementFirmBeneficiaryDTO" )
   // @Mapping(source = "suretyBond", target = "suretyBondDTO")
    @Mapping(source = "taskStatus", target = "taskStatusDTO")
    FundEgressDTO toDto(FundEgress fundEgress);

    @Mapping(source = "paymentStatusOptionDTO", target = "paymentStatusOption")
    @Mapping(source = "voucherPaymentTypeDTO", target = "voucherPaymentType")
    @Mapping(source = "voucherPaymentSubTypeDTO", target = "voucherPaymentSubType")
    @Mapping(source = "expenseTypeDTO", target = "expenseType")
    @Mapping(source = "expenseSubTypeDTO", target = "expenseSubType")
    @Mapping(source = "invoiceCurrencyDTO", target = "invoiceCurrency")
    @Mapping(source = "paymentCurrencyDTO", target = "paymentCurrency")
    @Mapping(source = "chargedCodeDTO", target = "chargedCode")
    @Mapping(source = "paymentModeDTO", target = "paymentMode")
    @Mapping(source = "transactionTypeDTO", target = "transactionType")
    @Mapping(source = "beneficiaryFeePaymentDTO", target = "beneficiaryFeePayment")
    @Mapping(source = "payoutToBeMadeFromCbsDTO", target = "payoutToBeMadeFromCbs")
    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    @Mapping(source = "ownerRegistryUnitDTO", target = "ownerRegistryUnit")
    @Mapping(source = "transferOwnerRegistryUnitDTO", target = "transferOwnerRegistryUnit" )
    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    @Mapping(source = "managementFirmBeneficiaryDTO", target = "managementFirmBeneficiary")
  //  @Mapping(source = "suretyBondDTO", target = "suretyBond")
    @Mapping(source = "taskStatusDTO", target = "taskStatus")
    FundEgress toEntity(FundEgressDTO fundEgressDTO);
}
