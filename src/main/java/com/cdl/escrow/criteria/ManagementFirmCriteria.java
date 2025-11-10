package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.*;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ManagementFirmCriteria implements Serializable {

    private LongFilter id;

    private StringFilter mfId;

    private StringFilter mfCif;

    private StringFilter mfName;

    private StringFilter mfNameLocal;

    private StringFilter mfLocation;

    private StringFilter mfReraNumber;

    private ZonedDateTimeFilter mfStartDate;

    private ZonedDateTimeFilter mfCompletionDate;

    private StringFilter mfPercentComplete;

    private DoubleFilter mfConstructionCost;

    private ZonedDateTimeFilter mfAccStatusDate;

    private ZonedDateTimeFilter mfRegistrationDate;

    private IntegerFilter mfNoOfUnits;

    private StringFilter mfRemarks;

    private StringFilter mfSpecialApproval;

    private StringFilter mfManagedBy;

    private StringFilter mfBackupUser;

    private StringFilter mfRetentionPercent;

    private StringFilter mfAdditionalRetentionPercent;

    private StringFilter mfTotalRetentionPercent;

    private ZonedDateTimeFilter mfRetentionEffectiveDate;

    private StringFilter mfManagementExpenses;

    private StringFilter mfMarketingExpenses;

    private ZonedDateTimeFilter mfAccoutStatusDate;

    private StringFilter mfTeamLeadName;

    private StringFilter mfRelationshipManagerName;

    private StringFilter mfAssestRelshipManagerName;

    private DoubleFilter mfRealEstateBrokerExp;

    private DoubleFilter mfAdvertisementExp;

    private StringFilter mfLandOwnerName;

    private LongFilter buildPartnerId;

    private LongFilter mfStatusId;

    private LongFilter mfTypeId;

    private LongFilter mfAccountStatusId;

    private LongFilter mfConstructionCostCurrencyId;

    //private LongFilter mflEstateAssestId;

    //private Set<BankAccountDTO> bankAccountDTOS ;

    //private Set<ManagementFirmBeneficiaryDTO> mflEstateAssestBeneficiaryDTOS ;

   // private Set<ManagementFirmClosureDTO> mflEstateAssestClosureDTOS ;

   // private Set<ManagementFirmFeeDTO> mflEstateAssestFeeDTOS ;

    // private Set<ManagementFirmHistoryDTO> mflEstateAssestFeeHistoryDTOS ;

   // private Set<ManagementFirmFinancialSummaryDTO> mflEstateAssestFinancialSummaryDTOS;

  //  private Set<OwnerRegistryUnitDTO> capitalPartnerUnitDTOS ;

  //  private Set<ProcessedFundIngressDTO> processedFundIngressDTOS ;

  //  private Set<PendingFundIngressDTO> pendingFundIngressDTOS;

  //  private Set<FundEgressDTO> fundEgressDTOS ;

  //  private Set<SuretyBondDTO> suretyBondDTOS;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;
}
