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

    private Double reafhAmount;

    private Double reafhTotalAmount;

    private Double reafhVatPercentage;

    private ZonedDateTime reafhTransactionDate;

    private Boolean reafhSuccess;

    private Boolean reafhStatus;

    private String reahfRemark;

    private String reafhFeeResponse;

    private String reafhResponseStatus;

    private String reafhSpecialField1;

    private String reafhSpecialField2;

    private String reafhSpecialField3;

    private String reafhSpecialField4;

    private String reafhSpecialField5;

    private String reafhFeeRequestBody;

    private ManagementFirmFeeDTO managementFirmFeeDTO;

    private ManagementFirmDTO managementFirmDTO;

    private Boolean deleted ;

    private boolean enabled ;
    // private OwnerRegistryUnitDTO capitalPartnerUnitDTO;

   // private FundEgressDTO fundEgressDTO;
}
