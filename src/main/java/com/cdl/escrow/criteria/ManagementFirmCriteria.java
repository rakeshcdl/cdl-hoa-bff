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

    private StringFilter reaId;

    private StringFilter reaCif;

    private StringFilter reaName;

    private StringFilter reaNameLocal;

    private StringFilter reaLocation;

    private StringFilter reaReraNumber;

    private ZonedDateTimeFilter reaStartDate;

    private ZonedDateTimeFilter reaCompletionDate;

    private StringFilter reaPercentComplete;

    private DoubleFilter reaConstructionCost;

    private ZonedDateTimeFilter reaAccStatusDate;

    private ZonedDateTimeFilter reaRegistrationDate;

    private IntegerFilter reaNoOfUnits;

    private StringFilter reaRemarks;

    private StringFilter reaSpecialApproval;

    private StringFilter reaManagedBy;

    private StringFilter reaBackupUser;

    private StringFilter reaRetentionPercent;

    private StringFilter reaAdditionalRetentionPercent;

    private StringFilter reaTotalRetentionPercent;

    private ZonedDateTimeFilter reaRetentionEffectiveDate;

    private StringFilter reaManagementExpenses;

    private StringFilter reaMarketingExpenses;

    private ZonedDateTimeFilter reaAccoutStatusDate;

    private StringFilter reaTeamLeadName;

    private StringFilter reaRelationshipManagerName;

    private StringFilter reaAssestRelshipManagerName;

    private DoubleFilter reaRealEstateBrokerExp;

    private DoubleFilter reaAdvertisementExp;

    private StringFilter reaLandOwnerName;

    private LongFilter buildPartnerId;

    private LongFilter reaStatusId;

    private LongFilter reaTypeId;

    private LongFilter reaAccountStatusId;

    private LongFilter reaConstructionCostCurrencyId;

    private LongFilter realEstateAssestId;

    //private Set<BankAccountDTO> bankAccountDTOS ;

    //private Set<ManagementFirmBeneficiaryDTO> realEstateAssestBeneficiaryDTOS ;

   // private Set<ManagementFirmClosureDTO> realEstateAssestClosureDTOS ;

   // private Set<ManagementFirmFeeDTO> realEstateAssestFeeDTOS ;

    // private Set<ManagementFirmHistoryDTO> realEstateAssestFeeHistoryDTOS ;

   // private Set<ManagementFirmFinancialSummaryDTO> realEstateAssestFinancialSummaryDTOS;

  //  private Set<CapitalPartnerUnitDTO> capitalPartnerUnitDTOS ;

  //  private Set<ProcessedFundIngressDTO> processedFundIngressDTOS ;

  //  private Set<PendingFundIngressDTO> pendingFundIngressDTOS;

  //  private Set<FundEgressDTO> fundEgressDTOS ;

  //  private Set<SuretyBondDTO> suretyBondDTOS;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;
}
