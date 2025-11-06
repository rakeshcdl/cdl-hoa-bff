package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryUnitTypeDTO implements Serializable {
    private Long id ;

    private String cputName;

    private byte[] cputIcon;

    private String cputIconContentType;

    private Boolean cputIsStandalone;

    private String cpUnitTypePrefix;

    private String cputExcelFormula;

    private String cputJsFormula;

    private OwnerRegistryUnitTypeDTO capitalPartnerParentUnitTypeDTO;

    private Set<OwnerRegistryUnitTypeDTO> capitalPartnerChildrenUnitTypeDTOS ;

    private Set<OwnerRegistryUnitDTO> ownerRegistryUnitDTOS;

    private Boolean deleted ;

    private boolean enabled ;
}
