package com.swarnava.ghostrider.dto;

import com.swarnava.ghostrider.entity.Rider;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RiderRequestMapper {
    RiderRequestMapper INSTANCE = Mappers.getMapper(RiderRequestMapper.class);

    Rider toEntity(CreateRiderDTO dto);
}
