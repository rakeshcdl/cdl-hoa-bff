package com.cdl.escrow.dto;


import com.cdl.escrow.enumeration.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementFirmDTO implements Serializable {
    private Long id;

    private String reaId;

    private String reaCif;

    private String reaName;

    private String reaNameLocal;

    private String reaLocation;

    private String reaReraNumber;

    private ZonedDateTime reaStartDate;

    private ZonedDateTime reaCompletionDate;

    private String reaPercentComplete;

    private Double reaConstructionCost;

    private ZonedDateTime reaAccStatusDate;

    private ZonedDateTime reaRegistrationDate;

    private Integer reaNoOfUnits;

    private String reaRemarks;

    private String reaSpecialApproval;

    private String reaManagedBy;

    private String reaBackupUser;

    private String reaRetentionPercent;

    private String reaAdditionalRetentionPercent;

    private String reaTotalRetentionPercent;

    private ZonedDateTime reaRetentionEffectiveDate;

    private String reaManagementExpenses;

    private String reaMarketingExpenses;

    private ZonedDateTime reaAccoutStatusDate;

    private String reaTeamLeadName;

    private String reaRelationshipManagerName;

    private String reaAssestRelshipManagerName;

    private Double reaRealEstateBrokerExp;

    private Double reaAdvertisementExp;

    private String reaLandOwnerName;

    private AssetRegisterDTO assetRegisterDTO;

    private ApplicationSettingDTO reaStatusDTO;

    private ApplicationSettingDTO reaTypeDTO;

    private ApplicationSettingDTO reaAccountStatusDTO;

    private ApplicationSettingDTO reaConstructionCostCurrencyDTO;

    private WorkflowStatus status;

    private ApplicationSettingDTO reaBlockPaymentTypeDTO;
    private Boolean deleted ;

    private boolean enabled ;
    // private transient Set<BankAccountDTO> bankAccountDTOS ;

    //private Set<ManagementFirmBeneficiaryDTO> realEstateAssestBeneficiaryDTOS ;

    //private Set<ManagementFirmClosureDTO> realEstateAssestClosureDTOS ;

    //private Set<ManagementFirmFeeDTO> realEstateAssestFeeDTOS ;

   // private Set<ManagementFirmHistoryDTO> realEstateAssestFeeHistoryDTOS ;

   // private Set<ManagementFirmFinancialSummaryDTO> realEstateAssestFinancialSummaryDTOS;

   // private Set<OwnerRegistryUnitDTO> capitalPartnerUnitDTOS ;

   // private Set<ProcessedFundIngressDTO> processedFundIngressDTOS ;

   // private Set<PendingFundIngressDTO> pendingFundIngressDTOS;

   // private Set<FundEgressDTO> fundEgressDTOS ;

   // private Set<SuretyBondDTO> suretyBondDTOS;

    private TaskStatusDTO taskStatusDTO;
}
