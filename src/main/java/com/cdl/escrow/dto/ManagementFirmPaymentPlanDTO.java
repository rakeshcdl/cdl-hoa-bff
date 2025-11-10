package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementFirmPaymentPlanDTO implements Serializable {

    private Long id;

    private Integer mfppInstallmentNumber;

    private Double mfppInstallmentPercentage;

    private Double mfppProjectCompletionPercentage;

    private ManagementFirmDTO managementFirmDTO;

    private Boolean deleted ;

    private boolean enabled ;
}
