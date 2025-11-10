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

    private String ownutName;

    private byte[] ownutIcon;

    private String ownutIconContentType;

    private Boolean ownutIsStandalone;

    private String ownUnitTypePrefix;

    private String ownutExcelFormula;

    private String ownutJsFormula;

    private OwnerRegistryUnitTypeDTO capitalPartnerParentUnitTypeDTO;

    private Set<OwnerRegistryUnitTypeDTO> capitalPartnerChildrenUnitTypeDTOS ;

    private Set<OwnerRegistryUnitDTO> ownerRegistryUnitDTOS;

    private Boolean deleted ;

    private boolean enabled ;
}
