package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryDTO implements Serializable {
    private Long id ;

    private String ownerRegistryId;

    private String ownerRegistryName;

    private String ownerRegistryMiddleName;

    private String ownerRegistryLastName;

    private Float ownerRegistryOwnershipPercentage;

    private String ownerRegistryIdNo;

    private String ownerRegistryTelephoneNo;

    private String ownerRegistryMobileNo;

    private String ownerRegistryEmail;

    private Integer ownerRegistryOwnerNumber;

    private Boolean isCurrent;

    private ZonedDateTime idExpiaryDate;

    private String ownerRegistryLocaleName;

    private ApplicationSettingDTO documentTypeDTO;

    private ApplicationSettingDTO countryOptionDTO;

    private ApplicationSettingDTO investorTypeDTO;

   private Set<OwnerRegistryBankInfoDTO> ownerRegistryBankInfoDTOS;

    private OwnerRegistryUnitDTO ownerRegistryUnitDTO;

    private Boolean deleted ;

    private boolean enabled ;

    private TaskStatusDTO taskStatusDTO;
}
