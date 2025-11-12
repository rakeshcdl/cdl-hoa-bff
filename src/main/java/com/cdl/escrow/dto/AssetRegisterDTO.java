package com.cdl.escrow.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssetRegisterDTO implements Serializable {
 private Long id;

 private String arDeveloperId;

 private String arCifrera;

 private String arDeveloperRegNo;

 private String arName;

 private String arMasterName;

 private String arNameLocal;

 private ZonedDateTime arOnboardingDate;

 private String arContactAddress;

 private String arContactTel;

 private String arPoBox;

 private String arMobile;

 private String arFax;

 private String arEmail;

 private String arLicenseNo;

 private ZonedDateTime arLicenseExpDate;

 private String arWorldCheckFlag;

 private String arWorldCheckRemarks;

 private Boolean arMigratedData;

    private String arRemark;

    private String arProjectName;

    private String arCompanyNumber;

    private String arMasterCommunity;

    private String arMasterDeveloper;


    private ApplicationSettingDTO arRegulatorDTO;

 private ApplicationSettingDTO arActiveStatusDTO;

 //private Set<Long> beneficiaryIds = new HashSet<>();
 private Boolean deleted ;

 private boolean enabled ;

 private TaskStatusDTO taskStatusDTO;

  //private Set<BuildPartnerBeneficiaryDTO> buildPartnerBeneficiaryDTOS ;

 //private Set<BuildPartnerContactDTO> buildPartnerContactDTOS;

// private Set<ManagementFirmFinancialSummaryDTO> realEstateAssestFinancialSummaryDTOS ;

// private Set<FundEgressDTO> fundEgressDTOS;
}
