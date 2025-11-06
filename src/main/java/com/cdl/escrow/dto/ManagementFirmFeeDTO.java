package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementFirmFeeDTO implements Serializable {
    private Long id;

    private Double reafAmount;

    private Double reafDebitAmount;

    private Double reafTotalAmount;

    private ZonedDateTime reafCalender;

    private ZonedDateTime reafCollectionDate;

    private ZonedDateTime reafNextRecoveryDate;

    private Double reafVatPercentage;

    private Boolean reafCollected;

    private Double reafFeePercentage;

    private ManagementFirmDTO managementFirmDTO;

    private ApplicationSettingDTO reafCategoryDTO;

    private ApplicationSettingDTO reafCurrencyDTO;

    private ApplicationSettingDTO reafFrequencyDTO;

    private ApplicationSettingDTO reafAccountTypeDTO;

    private Boolean deleted ;

    private boolean enabled ;
    // private Set<ManagementFirmHistoryDTO> realEstateAssestFeeHistoryDTOS ;
}
