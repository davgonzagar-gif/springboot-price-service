package com.davgonza.priceservice.infrastructure.entrypoint.rest.mapper;

import com.davgonza.priceservice.domain.model.Price;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.model.ApplicablePrice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface ApplicablePriceMapper {

    @Mapping(
            target = "price",
            expression = "java(price.getPrice().doubleValue())"
    )
    ApplicablePrice toResponse(Price price);

    default OffsetDateTime map(LocalDateTime value) {
        if (value == null) {
            return null;
        }
        return value.atOffset(ZoneOffset.UTC);
    }
}