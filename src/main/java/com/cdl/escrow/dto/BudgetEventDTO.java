package com.cdl.escrow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BudgetEventDTO implements Serializable {

    private Long id;

    private String eventId;

    private String urc;

    private ZonedDateTime timeStamp;

    private String syncType;

    private String propertyGroupId;

    private String periodCode;

    private String managementCompanyId;

    private Long acknowledgeRef;
}
