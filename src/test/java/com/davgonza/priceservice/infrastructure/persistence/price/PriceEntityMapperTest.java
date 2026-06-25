package com.davgonza.priceservice.infrastructure.persistence.price;

import com.davgonza.priceservice.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PriceEntityMapperTest {

    private final PriceEntityMapper mapper =
            Mappers.getMapper(PriceEntityMapper.class);

    @Test
    @DisplayName("Given price entity when mapping then return domain price")
    void givenPriceEntity_whenMapping_thenReturnDomainPrice() {

        // Given
        PriceEntity entity = PriceEntity.builder()
                .brandId(1L)
                .productId(35455L)
                .priceList(2)
                .priority(1)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .price(new BigDecimal("25.45"))
                .currency("EUR")
                .build();

        // When
        Price result = mapper.toDomain(entity);

        // Then
        assertNotNull(result);
        assertEquals(entity.getBrandId(), result.getBrandId());
        assertEquals(entity.getProductId(), result.getProductId());
        assertEquals(entity.getPriceList(), result.getPriceList());
        assertEquals(entity.getPriority(), result.getPriority());
        assertEquals(entity.getPrice(), result.getPrice());
        assertEquals(entity.getCurrency(), result.getCurrency());
    }
}