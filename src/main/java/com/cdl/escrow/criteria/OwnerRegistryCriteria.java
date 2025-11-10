package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OwnerRegistryCriteria implements Serializable {

    private LongFilter id ;

    private StringFilter ownerRegistryId;

    private StringFilter ownerRegistryName;

    private StringFilter ownerRegistryMiddleName;

    private StringFilter ownerRegistryLastName;

    private FloatFilter ownerRegistryOwnershipPercentage;

    private StringFilter ownerRegistryIdNo;

    private StringFilter ownerRegistryTelephoneNo;

    private StringFilter ownerRegistryMobileNo;

    private StringFilter ownerRegistryEmail;

    private IntegerFilter ownerRegistryOwnerNumber;

    private BooleanFilter isCurrent;

    private ZonedDateTimeFilter idExpiaryDate;

    private StringFilter ownerRegistryLocaleName;

    private LongFilter documentTypeId;

    private LongFilter countryOptionId;

    private LongFilter investorTypeId;

    //private Set<OwnerRegistryBankInfoDTO> ownerRegistryBankInfoDTOS ;

    private LongFilter ownerRegistryUnitId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;
}
