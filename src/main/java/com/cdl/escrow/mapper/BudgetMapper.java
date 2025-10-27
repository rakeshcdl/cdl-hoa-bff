package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BudgetDTO;
import com.cdl.escrow.dto.BudgetItemDTO;
import com.cdl.escrow.entity.Budget;
import com.cdl.escrow.entity.BudgetItem;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" )
public interface BudgetMapper  extends EntityMapper<BudgetDTO, Budget> {

    BudgetMapper INSTANCE = Mappers.getMapper(BudgetMapper.class);

    @Mapping(source = "budgetCategory", target = "budgetCategoryDTO")
    BudgetItemDTO toDto(BudgetItem budgetItem);

    @Mapping(source = "budgetCategoryDTO", target = "budgetCategory")
    BudgetItem toEntity(BudgetItemDTO budgetItemDTO);
}
