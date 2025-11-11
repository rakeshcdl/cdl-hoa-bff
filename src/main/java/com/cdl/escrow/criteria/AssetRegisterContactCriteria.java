package com.cdl.escrow.criteria;

import com.cdl.escrow.enumeration.WorkflowStatus;
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
public class AssetRegisterContactCriteria implements Serializable {

    private LongFilter id;

    private StringFilter arcContactName;

    private StringFilter arcContactTelCode;

    private StringFilter arcContactTelNo;

    private StringFilter arcCountryMobCode;

    private StringFilter arcContactMobNo;

    private StringFilter arcContactEmail;

    private StringFilter arcContactAddress;

    private StringFilter arcContactPoBox;

    private StringFilter arcContactFaxNo;

    private WorkflowStatus workflowStatus;

    private LongFilter assetRegisterId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;
}
