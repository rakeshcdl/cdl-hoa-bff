package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementFirmBeneficiaryDTO implements Serializable {
    private Long id;

    private String mfbBeneficiaryId;

    private String mfbName;

    private Double mfbContractAmount;

    private Double mfbActualLandPrice;

    private String mfbContractorName;

    private String mfbType;

    private String mfbBank;

    private String mfbSwift;

    private String mfbRoutingCode;

    private String mfbAddress;

    private String mfbBankAddress;

    private Boolean mfbIsActive;

    private Boolean mfbIsDeleted;

    private String mfbBeneAccount;

    private String mfbBeneIban;

    private ApplicationSettingDTO mfbTransferTypeDTO;

    private ApplicationSettingDTO mfbExpenseTypeDTO;

    private ApplicationSettingDTO mfbVendorSubTypeDTO;

    private ApplicationSettingDTO mfbContractorSubTypeDTO;

    private ApplicationSettingDTO mfbInfrastructureCategoryDTO;

    private ApplicationSettingDTO mfbSalesCategoryDTO;

   private Set<ManagementFirmDTO> managementFirmDTO = new HashSet<>() ;

   // private Set<Long> mflEstateAssestIds = new HashSet<>();

    private Boolean deleted ;

    private boolean enabled ;
    //private transient Set<BankAccount> bankAccountDTOS ;

   // private Set<FundEgress> fundEgressDTOS;
}
