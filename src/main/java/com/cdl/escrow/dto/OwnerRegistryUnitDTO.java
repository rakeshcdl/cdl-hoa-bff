package com.cdl.escrow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryUnitDTO implements Serializable {
    private Long id ;

    private String unitRefId;

    private String altUnitRefId;

    private String name;

    private Boolean isResale;

    private ZonedDateTime resaleDate;

    private String unitSysId;

    private String otherFormatUnitNo;

    private String virtualAccNo;

    private String towerName;

    private String unitPlotSize;

    private String floor;

    private String noofBedroom;

    private Boolean isModified;

    private OwnerRegistryUnitDTO partnerUnitDTO;

    private OwnerRegistryUnitTypeDTO ownerRegistryUnitTypeDTO;

    private ManagementFirmDTO managementFirmDTO;

    private ApplicationSettingDTO unitStatusDTO;

    private ApplicationSettingDTO propertyIdDTO;

    private ApplicationSettingDTO paymentPlanTypeDTO;

    private OwnerRegistryUnitBookingDTO ownerRegistryUnitBookingDTO;

    //private Set<ManagementFirmHistoryDTO> projectFeeHistoryDTOS ;

    private Set<OwnerRegistryUnitDTO> childOwnerRegistryUnitDTO;

    //private transient Set<BankAccountDTO> bankAccountDTOS ;

    private Set<OwnerRegistryDTO> ownerRegistryDTOS;

   // private Set<OwnerRegistryUnitPurchaseDTO> capitalPartnerUnitPurchaseDTOS ;

   // private Set<ProcessedFundIngressDTO> processedFundIngressDTOS;

    //private Set<PendingFundIngressDTO> pendingFundIngressDTOS;

   // private Set<FundEgressDTO> fundEgressDTOS;

  //  private Set<FundEgressDTO> transferFundEgressDTO;

    private Boolean deleted ;

    private boolean enabled ;
}
