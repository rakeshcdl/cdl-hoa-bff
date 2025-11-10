package com.cdl.escrow.mapper;

import com.cdl.escrow.dto.OwnerRegistryUnitPurchaseDTO;
import com.cdl.escrow.entity.OwnerRegistryUnitPurchase;
import com.cdl.escrow.helper.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" , uses = { ApplicationSettingMapper.class, OwnerRegistryMapper.class })
public interface OwnerRegistryUnitPurchaseMapper extends EntityMapper<OwnerRegistryUnitPurchaseDTO, OwnerRegistryUnitPurchase> {

    @Mapping(source = "ownupCreditCurrency", target = "ownupCreditCurrencyDTO")
    @Mapping(source = "ownuPurchasePriceCurrency", target = "ownuPurchasePriceCurrencyDTO")
    @Mapping(source = "ownerRegistryUnit", target = "ownerRegistryUnitDTO")
    OwnerRegistryUnitPurchaseDTO toDto(OwnerRegistryUnitPurchase ownerRegistryUnitPurchase);


    @Mapping(source = "ownupCreditCurrencyDTO", target = "ownupCreditCurrency")
    @Mapping(source = "ownuPurchasePriceCurrencyDTO", target = "ownuPurchasePriceCurrency")
    @Mapping(source = "ownerRegistryUnitDTO", target = "ownerRegistryUnit")
    OwnerRegistryUnitPurchase toEntity(OwnerRegistryUnitPurchaseDTO ownerRegistryUnitPurchaseDTO);
}
