package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" , uses = {AssetRegisterMapper.class,ManagementFirmMapper.class,
TaskStatusMapper.class})
public interface BudgetMapper  extends EntityMapper<BudgetDTO, Budget> {

    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

   // @Mapping(source = "budgetCategories", target = "budgetCategoriesDTOS")
    @Mapping(source = "assetRegister", target = "assetRegisterDTO")
    @Mapping(source = "managementFirm", target = "managementFirmDTO")
    BudgetDTO toDto(Budget budget);

  //  @Mapping(source = "budgetCategoriesDTOS", target = "budgetCategories")
    @Mapping(source = "assetRegisterDTO", target = "assetRegister")
    @Mapping(source = "managementFirmDTO", target = "managementFirm")
    Budget toEntity(BudgetDTO budgetDTO);
}
