package com.cdl.escrow.dto;


import com.cdl.escrow.entity.ApplicationSetting;
import com.cdl.escrow.enumeration.WorkflowStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.ManyToOne;
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

    private String mfId;

    private String mfCif;

    private String mfName;

    private String mfNameLocal;

    private String mfLocation;

    private String mfReraNumber;

    private ZonedDateTime mfStartDate;

    private ZonedDateTime mfCompletionDate;

    private String mfPercentComplete;

    private Double mfConstructionCost;

    private ZonedDateTime mfAccStatusDate;

    private ZonedDateTime mfRegistrationDate;

    private Integer mfNoOfUnits;

    private String mfRemarks;

    private String mfSpecialApproval;

    private String mfManagedBy;

    private String mfBackupUser;

    private String mfRetentionPercent;

    private String mfAdditionalRetentionPercent;

    private String mfTotalRetentionPercent;

    private ZonedDateTime mfRetentionEffectiveDate;

    private String mfManagementExpenses;

    private String mfMarketingExpenses;

    private ZonedDateTime mfAccoutStatusDate;

    private String mfTeamLeadName;

    private String mfRelationshipManagerName;

    private String mfAssestRelshipManagerName;

    private Double mfRealEstateBrokerExp;

    private Double mfAdvertisementExp;

    private String mfLandOwnerName;

    private AssetRegisterDTO assetRegisterDTO;

    private ApplicationSettingDTO mfStatusDTO;

    private ApplicationSettingDTO mfTypeDTO;

    private ApplicationSettingDTO mfAccountStatusDTO;

    private ApplicationSettingDTO mfConstructionCostCurrencyDTO;

    private WorkflowStatus status;

    private ApplicationSettingDTO mfBlockPaymentTypeDTO;
    private Boolean deleted ;

    private boolean enabled ;
    // private transient Set<BankAccountDTO> bankAccountDTOS ;

    //private Set<ManagementFirmBeneficiaryDTO> mflEstateAssestBeneficiaryDTOS ;

    //private Set<ManagementFirmClosureDTO> mflEstateAssestClosureDTOS ;

    //private Set<ManagementFirmFeeDTO> mflEstateAssestFeeDTOS ;

   // private Set<ManagementFirmHistoryDTO> mflEstateAssestFeeHistoryDTOS ;

   // private Set<ManagementFirmFinancialSummaryDTO> mflEstateAssestFinancialSummaryDTOS;

   // private Set<OwnerRegistryUnitDTO> capitalPartnerUnitDTOS ;

   // private Set<ProcessedFundIngressDTO> processedFundIngressDTOS ;

   // private Set<PendingFundIngressDTO> pendingFundIngressDTOS;

   // private Set<FundEgressDTO> fundEgressDTOS ;

   // private Set<SuretyBondDTO> suretyBondDTOS;

    private TaskStatusDTO taskStatusDTO;

    // New added
    private ApplicationSettingDTO escrowTypeDTO;

    private String accountOwner;

    private String backupAccountOwner;

    private ApplicationSettingDTO emailNotificationDTO;
}
