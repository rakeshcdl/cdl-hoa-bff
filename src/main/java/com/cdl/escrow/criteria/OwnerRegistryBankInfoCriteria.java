package com.cdl.escrow.criteria;

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
public class OwnerRegistryBankInfoCriteria implements Serializable {

    private LongFilter id ;

    private StringFilter ownbiPayeeName;

    private StringFilter ownbiPayeeAddress;

    private StringFilter ownbiBankName;

    private StringFilter ownbiBankAddress;

    private StringFilter ownbiBicCode;

    private StringFilter ownbiBeneRoutingCode;

    private LongFilter bankAccountId;

    private LongFilter capitalPartnerId;

    private LongFilter payModeId;
}
