package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementFirmClosureDTO implements Serializable {
    private Long id;

    private Double mfcTotalIncomeFund;

    private Double mfcTotalPayment;

    private Double mfcCheckGuaranteeDoc;

    private Boolean deleted ;

    private boolean enabled ;
    private ManagementFirmDTO managementFirmDTO;

    private ApplicationSettingDTO mfcDocumentDTO;
}
