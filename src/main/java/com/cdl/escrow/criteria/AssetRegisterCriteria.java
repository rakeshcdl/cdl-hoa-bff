package com.cdl.escrow.criteria;

import com.cdl.escrow.filter.BooleanFilter;
import com.cdl.escrow.filter.LongFilter;
import com.cdl.escrow.filter.StringFilter;
import com.cdl.escrow.filter.ZonedDateTimeFilter;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class AssetRegisterCriteria implements Serializable {

    private LongFilter id;

    private StringFilter arDeveloperId;

    private StringFilter arCifrera;

    private StringFilter arDeveloperRegNo;

    private StringFilter arName;

    private StringFilter arMasterName;

    private StringFilter arNameLocal;

    private ZonedDateTimeFilter arOnboardingDate;

    private StringFilter arContactAddress;

    private StringFilter arContactTel;

    private StringFilter arPoBox;

    private StringFilter arMobile;

    private StringFilter arFax;

    private StringFilter arEmail;

    private StringFilter arLicenseNo;

    private ZonedDateTimeFilter arLicenseExpDate;

    private StringFilter arWorldCheckFlag;

    private StringFilter arWorldCheckRemarks;

    private BooleanFilter arMigratedData;

    private StringFilter arremark;

    private LongFilter arRegulatorId;

    private LongFilter arActiveStatusId;

    private BooleanFilter enabled ;

    private BooleanFilter deleted;

   // private Set<BuildPartnerBeneficiaryDTO> buildPartnerBeneficiaryDTOS ;

    //private Set<BuildPartnerContactDTO> buildPartnerContactDTOS;

    //private Set<ManagementFirmFinancialSummaryDTO> realEstateAssestFinancialSummaryDTOS ;

    //private Set<FundEgressDTO> fundEgressDTOS;
}
