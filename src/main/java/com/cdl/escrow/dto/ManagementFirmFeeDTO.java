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

    private Double mffAmount;

    private Double mffDebitAmount;

    private Double mffTotalAmount;

    private ZonedDateTime mffCalender;

    private ZonedDateTime mffCollectionDate;

    private ZonedDateTime mffNextRecoveryDate;

    private Double mffVatPercentage;

    private Boolean mffCollected;

    private Double mffFeePercentage;

    private ManagementFirmDTO managementFirmDTO;

    private ApplicationSettingDTO mffCategoryDTO;

    private ApplicationSettingDTO mffCurrencyDTO;

    private ApplicationSettingDTO mffFrequencyDTO;

    private ApplicationSettingDTO mffAccountTypeDTO;

    private Boolean deleted ;

    private boolean enabled ;
    // private Set<ManagementFirmHistoryDTO> mflEstateAssestFeeHistoryDTOS ;

    private String mffCorporateTax;
}
