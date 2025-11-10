package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OwnerRegistryUnitTypeCriteria implements Serializable {

    private LongFilter id ;

    private StringFilter ownutName;

   // private byte[] ownutIcon;

    private StringFilter ownutIconContentType;

    private BooleanFilter ownutIsStandalone;

    private StringFilter ownUnitTypePrefix;

    private StringFilter ownutExcelFormula;

    private StringFilter ownutJsFormula;

    private LongFilter capitalPartnerParentUnitTypeId;

    //private Set<OwnerRegistryUnitTypeDTO> capitalPartnerChildrenUnitTypeDTOS ;

    //private Set<OwnerRegistryUnitDTO> capitalPartnerUnitDTOS;
}
