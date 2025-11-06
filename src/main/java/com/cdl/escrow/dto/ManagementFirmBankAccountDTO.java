package com.cdl.escrow.dto;


import com.cdl.escrow.enumeration.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagementFirmBankAccountDTO implements Serializable {

    private Long id ;


    private String accountType; // TRUST, RETENTION, SUB_CONSTRUCTION, CORPORATE

    private String accountNumber;

    private String ibanNumber;

    private ZonedDateTime dateOpened;

    private String accountTitle;

    private String currencyCode;

    private Boolean isValidated ;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;


    private WorkflowStatus status;

    private boolean enabled ;

    private Boolean deleted ;

    private ManagementFirmDTO managementFirmDTO;
}
