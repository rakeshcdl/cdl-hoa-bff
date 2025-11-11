package com.cdl.escrow.dto;

import com.cdl.escrow.enumeration.WorkflowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetRegisterContactDTO implements Serializable {
    private Long id;

    private String arcContactName;

    private String arcFirstName;

    private String arcLastName;

    private String arcContactTelCode;

    private String arcContactTelNo;

    private String arcCountryMobCode;

    private String arcContactMobNo;

    private String arcContactEmail;

    private String arcContactAddress;

    private String arcContactAddressLine1;

    private String arcContactAddressLine2;

    private String arcContactPoBox;

    private String arcContactFaxNo;

    private boolean enabled;

    private WorkflowStatus workflowStatus;

    private AssetRegisterDTO assetRegisterDTO;

    private Boolean deleted ;
}
