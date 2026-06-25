package com.davgonza.priceservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Price {

    private final Long brandId;
    private final Long productId;
    private final Integer priceList;
    private final Integer priority;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final BigDecimal price;
    private final String currency;
}