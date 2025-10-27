package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BudgetCategoryDTO;
import com.cdl.escrow.entity.BudgetCategory;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" )
public interface BudgetCategoryMapper extends EntityMapper<BudgetCategoryDTO, BudgetCategory> {

    BudgetCategoryMapper INSTANCE = Mappers.getMapper(BudgetCategoryMapper.class);

    @Mapping(source = "budget", target = "budgetDTO")
    @Mapping(source = "budgetItems", target = "budgetItemDTOS")
    BudgetCategoryDTO toDto(BudgetCategory budgetCategory);

    @Mapping(source = "budgetDTO", target = "budget")
    @Mapping(source = "budgetItemDTOS", target = "budgetItems")
    BudgetCategory toEntity(BudgetCategoryDTO budgetCategoryDTO);
}

