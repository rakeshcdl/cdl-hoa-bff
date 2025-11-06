package com.cdl.escrow.criteria;

import com.cdl.escrow.enumeration.WorkflowStatus;
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
public class AssetRegisterContactCriteria implements Serializable {

    private LongFilter id;

    private StringFilter arcContactName;

    private StringFilter arcCcontactTelCode;

    private StringFilter arcCcontactTelNo;

    private StringFilter arcCcountryMobCode;

    private StringFilter arcCcontactMobNo;

    private StringFilter arcCcontactEmail;

    private StringFilter arcCcontactAddress;

    private StringFilter arcCcontactPoBox;

    private StringFilter arcCcontactFaxNo;

    private WorkflowStatus workflowStatus;

    private LongFilter assetRegisterId;
}
