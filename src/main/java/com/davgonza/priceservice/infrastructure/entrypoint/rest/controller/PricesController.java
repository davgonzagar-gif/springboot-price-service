package com.davgonza.priceservice.infrastructure.entrypoint.rest.controller;

import com.davgonza.priceservice.application.port.in.GetApplicablePriceUseCase;
import com.davgonza.priceservice.domain.model.Price;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.api.PricesApi;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.mapper.ApplicablePriceMapper;
import com.davgonza.priceservice.infrastructure.entrypoint.rest.model.ApplicablePrice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequiredArgsConstructor
public class PricesController implements PricesApi {

    private final GetApplicablePriceUseCase getApplicablePriceUseCase;

    private final ApplicablePriceMapper applicablePriceMapper;

    @Override
    public ResponseEntity<ApplicablePrice> findApplicablePrice(
            OffsetDateTime applicationDate,
            Long productId,
            Long brandId) {

        Price price = getApplicablePriceUseCase.getApplicablePrice(
                applicationDate.toLocalDateTime(),
                productId,
                brandId
        );

        return ResponseEntity.ok(
                applicablePriceMapper.toResponse(price)
        );
    }
}