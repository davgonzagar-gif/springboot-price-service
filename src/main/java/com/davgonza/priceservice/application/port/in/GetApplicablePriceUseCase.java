package com.davgonza.priceservice.application.port.in;

import com.davgonza.priceservice.domain.model.Price;

import java.time.LocalDateTime;

public interface GetApplicablePriceUseCase {

    Price getApplicablePrice(
            LocalDateTime applicationDate,
            Long productId,
            Long brandId
    );
}