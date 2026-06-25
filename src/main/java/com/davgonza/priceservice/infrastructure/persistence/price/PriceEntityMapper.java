package com.davgonza.priceservice.infrastructure.persistence.price;

import com.davgonza.priceservice.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceEntityMapper {

    Price toDomain(PriceEntity entity);
}