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


    BudgetCategoryDTO toDto(BudgetCategory budgetCategory);

    BudgetCategory toEntity(BudgetCategoryDTO budgetCategoryDTO);
}

