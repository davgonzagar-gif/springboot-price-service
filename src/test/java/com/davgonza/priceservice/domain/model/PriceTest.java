package com.davgonza.priceservice.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Price Domain Model Tests")
class PriceTest {

    @Test
    @DisplayName("Given valid price data when creating a price then all fields are accessible")
    void givenValidPriceData_whenCreatingPrice_thenAllFieldsAreAccessible() {

        Long brandId = 1L;
        Long productId = 35455L;
        Integer priceList = 1;
        Integer priority = 0;
        LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);
        BigDecimal price = new BigDecimal("35.50");
        String currency = "EUR";

        Price model = new Price(
                brandId,
                productId,
                priceList,
                priority,
                startDate,
                endDate,
                price,
                currency
        );

        assertAll(
                () -> assertEquals(brandId, model.getBrandId()),
                () -> assertEquals(productId, model.getProductId()),
                () -> assertEquals(priceList, model.getPriceList()),
                () -> assertEquals(priority, model.getPriority()),
                () -> assertEquals(startDate, model.getStartDate()),
                () -> assertEquals(endDate, model.getEndDate()),
                () -> assertEquals(price, model.getPrice()),
                () -> assertEquals(currency, model.getCurrency())
        );
    }
}