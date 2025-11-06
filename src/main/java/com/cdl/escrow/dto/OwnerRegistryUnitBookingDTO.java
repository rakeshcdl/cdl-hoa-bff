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

    private Double cpubAmountPaid;

    private Double cpubAreaSize;

    private Double cpubForFeitAmount;

    private Double cpubDldAmount;

    private Double cpubRefundAmount;

    private String cpubRemarks;

    private Double cpubTransferredAmount;

    private Set<OwnerRegistryUnitDTO> ownerRegistryUnitDTOS;

    private Boolean deleted ;

    private boolean enabled ;
}
