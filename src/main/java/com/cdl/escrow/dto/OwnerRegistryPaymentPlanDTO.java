package com.cdl.escrow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryPaymentPlanDTO implements Serializable {

    private Long id;

    private Integer ownppInstallmentNumber;

    private ZonedDateTime ownppInstallmentDate;

    private Double ownppBookingAmount;

    private OwnerRegistryDTO ownerRegistryDTO;

    private Boolean deleted ;

    private boolean enabled ;
}
