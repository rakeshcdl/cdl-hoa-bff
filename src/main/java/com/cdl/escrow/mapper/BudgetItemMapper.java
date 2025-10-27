package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.entity.BudgetItem;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" )
public interface BudgetItemMapper extends EntityMapper<BudgetItemDTO, BudgetItem> {

    BudgetItemMapper INSTANCE = Mappers.getMapper(BudgetItemMapper.class);

    @Mapping(source = "budgetCategory", target = "budgetCategoryDTO")
    BudgetItemDTO toDto(BudgetItem budgetItem);

    @Mapping(source = "budgetCategoryDTO", target = "budgetCategory")
    BudgetItem toEntity(BudgetItemDTO budgetItemDTO);
}
