package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryUnitBookingDTO implements Serializable {
    private Long id ;

    private Double ownubAmountPaid;

    private Double ownubAreaSize;

    private Double ownubForFeitAmount;

    private Double ownubDldAmount;

    private Double ownubRefundAmount;

    private String ownubRemarks;

    private Double ownubTransferredAmount;

    private Set<OwnerRegistryUnitDTO> ownerRegistryUnitDTOS;

    private Boolean deleted ;

    private boolean enabled ;
}
