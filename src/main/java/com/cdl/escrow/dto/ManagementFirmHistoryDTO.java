package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagementFirmHistoryDTO implements Serializable {
    private Long id;

    private Double mffhAmount;

    private Double mffhTotalAmount;

    private Double mffhVatPercentage;

    private ZonedDateTime mffhTransactionDate;

    private Boolean mffhSuccess;

    private Boolean mffhStatus;

    private String mfhfRemark;

    private String mffhFeeResponse;

    private String mffhResponseStatus;

    private String mffhSpecialField1;

    private String mffhSpecialField2;

    private String mffhSpecialField3;

    private String mffhSpecialField4;

    private String mffhSpecialField5;

    private String mffhFeeRequestBody;

    private ManagementFirmFeeDTO managementFirmFeeDTO;

    private ManagementFirmDTO managementFirmDTO;

    private Boolean deleted ;

    private boolean enabled ;
    // private OwnerRegistryUnitDTO capitalPartnerUnitDTO;

   // private FundEgressDTO fundEgressDTO;
}
