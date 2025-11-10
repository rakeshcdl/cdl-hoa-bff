package com.cdl.escrow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryContactDetailDTO implements Serializable {

    private Long id ;

    private String jointwnDetailsContactName;

    private String jointownDetailsFirstName;

    private String jointownDetailsLastName;

    private String jointownDetailsContactTelCode;

    private String jointownDetailsContactTelNo;

    private String jointownDetailsCountryMobCode;

    private String jointownDetailsContactMobNo;

    private String jointownDetailsContactEmail;

    private String jointownDetailsContactAddress;

    private String jointownDetailsContactAddressLine1;

    private String jointownDetailsContactAddressLine2;

    private String jointownDetailsContactPoBox;

    private String jointownDetailsContactFaxNo;

    private Boolean enabled ;

    private Boolean deleted;


    private TaskStatusDTO taskStatusDTO;

    private OwnerRegistryDTO ownerRegistryDTO;
}
