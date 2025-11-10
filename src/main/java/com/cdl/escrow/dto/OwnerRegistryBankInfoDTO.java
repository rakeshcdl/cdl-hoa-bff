package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerRegistryBankInfoDTO implements Serializable {
    private Long id ;

    private String ownbiPayeeName;

    private String ownbiPayeeAddress;

    private String ownbiBankName;

    private String ownbiBankAddress;

    private String ownbiBicCode;

    private String ownbiBeneRoutingCode;

    private String ownbiAccountNumber;

    private String ownbiIban;

    //private  transient BankAccountDTO bankAccountDTO;

    private OwnerRegistryDTO ownerRegistryDTO;

    private ApplicationSettingDTO payModeDTO;
    private Boolean deleted ;

    private boolean enabled ;
}
