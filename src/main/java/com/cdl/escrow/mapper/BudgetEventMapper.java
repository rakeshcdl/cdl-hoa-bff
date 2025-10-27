package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.BudgetEventDTO;
import com.cdl.escrow.entity.BudgetEvent;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring" )
public interface BudgetEventMapper extends EntityMapper<BudgetEventDTO, BudgetEvent> {

    BudgetEventMapper INSTANCE = Mappers.getMapper(BudgetEventMapper.class);

    BudgetEventDTO toDto(BudgetEvent budgetEvent);

    BudgetEvent toEntity(BudgetEventDTO budgetEventDTO);
}

