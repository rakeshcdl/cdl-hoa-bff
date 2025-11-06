package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.ManagementFirmBankAccountDTO;
import com.cdl.escrow.entity.ManagementFirmBankAccount;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ManagementFirmMapper.class })
public interface ManagementFirmBankAccountMapper extends EntityMapper<ManagementFirmBankAccountDTO, ManagementFirmBankAccount> {

    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    ManagementFirmBankAccountDTO toDto(ManagementFirmBankAccount managementFirmBankAccount);

    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    ManagementFirmBankAccount toEntity(ManagementFirmBankAccountDTO managementFirmBankAccountDTO);
}
