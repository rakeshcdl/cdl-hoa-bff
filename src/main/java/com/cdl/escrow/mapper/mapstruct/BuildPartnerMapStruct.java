package com.cdl.escrow.mapper.mapstruct;


import com.cdl.escrow.dto.record.BuildPartnerRecord;
import com.cdl.escrow.entity.AssetRegister;
import com.cdl.escrow.mapper.*;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface BuildPartnerMapStruct {

    AssetRegisterMapper INSTANCE = Mappers.getMapper(AssetRegisterMapper.class);

    @Mapping(source = "status", target = "status") // enum -> string default handled
    BuildPartnerRecord toDto(AssetRegister entity);
}
